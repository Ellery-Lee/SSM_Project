package com.liruicong.eduservice.controller;


import com.liruicong.commonutils.R;
import com.liruicong.eduservice.client.VodClient;
import com.liruicong.eduservice.entity.EduVideo;
import com.liruicong.eduservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author liruicong
 * @since 2021-06-22
 */
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {
    @Autowired
    private EduVideoService videoService;
    //注入vodClient
    @Autowired
    private VodClient vodClient;
    //添加小节
    @PostMapping("addVideo")
    public R addVideo(@RequestBody EduVideo eduVideo){
        videoService.save(eduVideo);
        return R.ok();
    }
    //删除小节
    //TODO后面这个方法需要完善：删除小节的时候，同时把阿里云里面视频删除
    @DeleteMapping("{id}")
    public R deleteVideo(@PathVariable String id){
        //根据小节id获取视频id，调用方法实现视频删除
        videoService.removeAlyVideo(id);
        return R.ok();
    }
    //根据小节id查询
    @GetMapping("getVideoInfo/{videoId}")
    public R getChapterInfo(@PathVariable String videoId){
        EduVideo eduVideo = videoService.getById(videoId);
        return R.ok().data("video", eduVideo);
    }
    //修改小节
    @PostMapping("updateVideo")
    public R updateVideo(@RequestBody EduVideo eduVideo){
        videoService.updateById(eduVideo);
        return R.ok();
    }
}

