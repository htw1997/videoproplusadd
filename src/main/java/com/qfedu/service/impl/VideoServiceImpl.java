package com.qfedu.service.impl;

import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;
import com.qfedu.mapper.CourseMapper;
import com.qfedu.mapper.VideoMapper;
import com.qfedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    VideoMapper mapper;

    @Autowired
    CourseMapper courseMapper;

    @Override
    public Video selectVideoById(int id) {
        Video video = mapper.selectVideoById(id);
        return video;
    }

    @Override
    public List<Video> getVideoListByCourseId(int id) {
        List<Video> videos = mapper.getVideoListByCourseId(id);
        return videos;
    }

    @Override
    public Video getVideoById(Integer videoId) {
        return mapper.getVideoById(videoId);
    }

    @Override
    public void addPlayNum(Video video) {
        mapper.addPlayNum(video);
    }

    @Override
    public List<Video> selectListByQueryVo(VideoQueryVo queryVo) {
        List<Video> videos = mapper.selectVideoByQueryVo(queryVo);
        return videos;

    }

    @Override
    public int saveVideo(Video video) {
        int result = mapper.saveVideo(video);
        System.out.println("saveVideo: " + result);
        return result;
    }

    @Override
    public int delBatchVideos(Integer[] ids) {
        VideoQueryVo videoQueryVo = new VideoQueryVo();
        videoQueryVo.setIds(Arrays.asList(ids));
        //Arrays.asList(ids) 将数组变成一个list组合，但是该集合是只读的
        int result = mapper.deleteVideosByIds(videoQueryVo);
        return result;
    }

    @Override
    public List<Video> videoListAll(VideoQueryVo videoByVo) {

        List<Video> list = mapper.videoListAll(videoByVo);

        return list;
    }

    @Override
    public int updateVideos(Video video) {
        int result = mapper.updateVideos(video);
        System.out.println("update:" + result);
        return result;
    }

}
