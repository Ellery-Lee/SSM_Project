package com.liruicong.eduservice.service;

import com.liruicong.eduservice.entity.EduVideo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程视频 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-22
 */
public interface EduVideoService extends IService<EduVideo> {
    //根据课程id删除小节
    void removeVideoByCourseId(String courseId);
}
