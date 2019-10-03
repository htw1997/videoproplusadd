package com.qfedu.mapper;

import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface VideoMapper {

    int saveVideo(Video video);

    List<Video> selectVideoByQueryVo(VideoQueryVo queryVo);

    int deleteVideosByIds(VideoQueryVo queryVo);

    Video selectVideoById(int id);

    int updateVideos(Video video);


    List<Video> getVideoListByCourseId(int id);

    Video getVideoById(Integer videoId);

    List<Video> videoListAll(VideoQueryVo videoByVo);

    List<Video> findVideoByCid(int courseId);
    @Update("update video set play_num = #{playNum} where id = #{id}")
    void addPlayNum(Video video);
}
