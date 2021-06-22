package com.liruicong.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liruicong.eduservice.entity.EduSubject;
import com.liruicong.eduservice.entity.excel.SubjectData;
import com.liruicong.eduservice.service.EduSubjectService;
import com.liruicong.servicebase.exceptionhandler.GuliException;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    //因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
    //不能实现数据库操作，这里使用有参构造方法传入service解决
    public EduSubjectService subjectService;
    //无参构造
    public SubjectExcelListener() {
    }
    //有参构造
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if(subjectData == null){
            throw new GuliException(20001, "文件数据为空");
        }
        //一行一行读取，每次读取有两个值，第一个值是一级分类，第二个值是二级分类
        //判断一级分类是否重复
        EduSubject existOneSubject = this.existOneSubject(subjectData.getOneSubjectName());
        if(existOneSubject == null){//没有相同的一级分类，进行添加
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());//一级分类名称
            subjectService.save(existOneSubject);
        }

        //得到一级分类id值，创建EduSubject，id自动生成
        String pid = existOneSubject.getId();
        //添加二级分裂
        //判断二级分类是否重复
        EduSubject existTwoSubject = this.existTwoSubject(subjectData.getTwoSubjectName(), pid);
        if(existTwoSubject == null){
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());//二级分类名称
            subjectService.save(existTwoSubject);
        }
    }
    //判断一级分类不能重复添加
    private EduSubject existOneSubject(String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);//一级分类名称
        return oneSubject;
    }

    //判断二级分类不能重复添加
    private EduSubject existTwoSubject(String name, String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
