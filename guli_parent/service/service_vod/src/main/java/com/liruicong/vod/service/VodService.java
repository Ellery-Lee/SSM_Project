package com.liruicong.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {
    //上传视频到阿里云
    String uploadVideoAly(MultipartFile file);
    //根据视频id删除视频
    void deleteVideoAly(String id);
    //删除多个阿里云视频方法
    void removeMoreAlyVideo(List<String> videoIdList);
}
