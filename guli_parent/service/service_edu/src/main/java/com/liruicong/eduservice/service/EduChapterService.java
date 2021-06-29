package com.liruicong.eduservice.service;

import com.liruicong.eduservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liruicong.eduservice.entity.chapter.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author liruicong
 * @since 2021-06-22
 */
public interface EduChapterService extends IService<EduChapter> {
    //课程大纲列表,根据课程id进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
    //删除章节
    boolean deleteChapter(String chapterId);
    //根据课程id删除章节
    void removeChapterByCourseId(String courseId);
}
