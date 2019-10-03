package com.qfedu.service;

import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;

import java.util.List;

public interface VideoService {


    List<Video> selectListByQueryVo(VideoQueryVo queryVo);


    int saveVideo(Video video);


    int updateVideos(Video video);

    int delBatchVideos(Integer[] ids);

    List<Video> videoListAll(VideoQueryVo videoByVo);


    Video selectVideoById(int id);


    List<Video> getVideoListByCourseId(int id);

    Video getVideoById(Integer videoId);

    void addPlayNum(Video video);
}
