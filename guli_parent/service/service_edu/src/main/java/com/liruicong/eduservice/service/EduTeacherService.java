package com.liruicong.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liruicong.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-07
 */
public interface EduTeacherService extends IService<EduTeacher> {
    //1、分页查询讲师
    Map<String,Object> getTeacherFrontList(Page<EduTeacher> pageTeacher);
}
