package com.liruicong.vod.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.liruicong.commonutils.R;
import com.liruicong.servicebase.exceptionhandler.GuliException;
import com.liruicong.vod.service.VodService;
import com.liruicong.vod.utils.ConstantVodUtils;
import com.liruicong.vod.utils.InitVodClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/eduvod/video")
//@CrossOrigin
public class VodController {
    @Autowired
    private VodService vodService;
    //上传视频到阿里云
    @PostMapping("uploadAlyVideo")
    public R uploadAlyVideo(MultipartFile file){
        //返回上传视频id
        String videoId = vodService.uploadVideoAly(file);
        return R.ok().data("videoId", videoId);
    }
    //根据视频id删除视频
    @DeleteMapping("removeAlyVideo/{id}")
    public R removeAlyVideo(@PathVariable String id){
        vodService.deleteVideoAly(id);
        return R.ok();
    }

    //删除多个阿里云视频方法
    //参数是多个视频id
    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam("videoIdList") List<String> videoIdList){
        vodService.removeMoreAlyVideo(videoIdList);
        return R.ok();
    }
    //根据视频id获取视频凭证
    @GetMapping("getPlayAuth/{id}")
    public R getPlayAuth(@PathVariable String id){
        try {
            System.out.println(id);
            //创建初始化对象
            DefaultAcsClient client = InitVodClient.initVodClient(ConstantVodUtils.ACCESS_KEY_ID, ConstantVodUtils.ACCESS_KEY_SECRET);
            //创建获取凭证request和respond对象
            GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
            //向request设置视频id
            request.setVideoId(id);
            //调用方法得到凭证
            GetVideoPlayAuthResponse acsResponse = client.getAcsResponse(request);
            String playAuth = acsResponse.getPlayAuth();
            return R.ok().data("playAuth", playAuth);
        }catch (Exception e){
            throw new GuliException(20001, "获取凭证失败");
        }
    }
}
