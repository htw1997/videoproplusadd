package com.qfedu.mapper;

import com.qfedu.entry.Speaker;

import java.util.List;

public interface SpeakerMapper {

    List<Speaker> selectSpeakerList();

    int updateSpeaker(Speaker speaker);


    int addSpeaker(Speaker speaker);

    Speaker selectSpeakerById(int id);

    int deleteSpeakerById(Integer id);
}
