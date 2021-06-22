package com.liruicong.eduservice.service;

import com.liruicong.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liruicong.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-21
 */
public interface EduSubjectService extends IService<EduSubject> {
    //添加课程分类
    void saveSubject(MultipartFile file, EduSubjectService eduSubjectService);
    //课程分类列表(树形)
    List<OneSubject> getAllOneTwoSubject();
}
