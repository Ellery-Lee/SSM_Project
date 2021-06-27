package com.liruicong.eduservice.service;

import com.liruicong.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liruicong.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-22
 */
public interface EduCourseService extends IService<EduCourse> {
    //添加课程基本信息的方法
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
