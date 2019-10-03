package com.qfedu.service;

import com.qfedu.entry.Speaker;

import java.util.List;

public interface SpeakerService {

     int deleteSpeakerById(Integer id);


    int updateSpeaker(Speaker speaker);

    List<Speaker> selectAllSpeaker(int page, int pageSize);

    List<Speaker> selectAllSpeaker();

    int addSpeaker(Speaker speaker);

    Speaker selectSpeakerById(int id);

}
