package com.liruicong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.liruicong.commonutils.R;
import com.liruicong.eduservice.client.VodClient;
import com.liruicong.eduservice.entity.EduVideo;
import com.liruicong.eduservice.mapper.EduVideoMapper;
import com.liruicong.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liruicong.servicebase.exceptionhandler.GuliException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-22
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {
    //注入vodClient
    @Autowired
    private VodClient vodClient;
    //根据课程id删除小节
    @Override
    public void removeVideoByCourseId(String courseId) {
        //1、根据课程id查询所有的视频id
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id", courseId);
        wrapperVideo.select("video_source_id");
        List<EduVideo> eduVideoList = this.list(wrapperVideo);
        //List<EduVideo>编程List<String>
        List<String> videoIds = new ArrayList<>();
        for (int i = 0; i < eduVideoList.size(); i++) {
            EduVideo eduVideo = eduVideoList.get(i);
            String videoSourceId = eduVideo.getVideoSourceId();
            if(!StringUtils.isEmpty(videoSourceId)){
                //放到videoIds集合里面
                videoIds.add(videoSourceId);
            }
        }
        //根据多个视频id删除多个视频
        if(videoIds.size() > 0){
            vodClient.deleteBatch(videoIds);
        }

        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id", courseId);
        this.remove(wrapper);
    }

    //根据小节id获取视频id，调用方法实现视频删除
    @Override
    public void removeAlyVideo(String id) {
        EduVideo eduVideo = this.getById(id);
        String videoSourceId = eduVideo.getVideoSourceId();
        if(!StringUtils.isEmpty(videoSourceId)){
            //根据视频id删除对应阿里云视频文件
            R result = vodClient.removeAlyVideo(eduVideo.getVideoSourceId());
            if(result.getCode() == 20001){
                throw new GuliException(20001, "删除视频失败，熔断器...");
            }
        }
        this.removeById(id);
    }
}
