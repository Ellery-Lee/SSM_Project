package com.liruicong.eduservice.service.impl;

import com.liruicong.eduservice.entity.EduCourse;
import com.liruicong.eduservice.entity.EduCourseDescription;
import com.liruicong.eduservice.entity.vo.CourseInfoVo;
import com.liruicong.eduservice.mapper.EduCourseMapper;
import com.liruicong.eduservice.service.EduCourseDescriptionService;
import com.liruicong.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liruicong.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-22
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {
    //课程描述注入
    @Autowired
    private EduCourseDescriptionService courseDescriptionService;
    //添加课程基本信息的方法
    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //1、向课程表添加课程基本信息
        //CourseInfoVo对象转换eduCourse对象
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        boolean save = this.save(eduCourse);
        if(!save){
            //添加失败
            throw new GuliException(20001, "添加课程信息失败");
        }
        //获取添加之后的课程id
        String cid = eduCourse.getId();
        //2、向课程简介表添加课程简介
        //edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //设置描述id为课程id
        courseDescription.setId(cid);
        courseDescriptionService.save(courseDescription);
        return cid;
    }
}
