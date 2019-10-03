package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entry.Speaker;
import com.qfedu.mapper.SpeakerMapper;
import com.qfedu.service.SpeakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpeakerServiceImpl implements SpeakerService {
    @Autowired
    SpeakerMapper mapper;


    @Override
    public int deleteSpeakerById(Integer id) {
        int result = mapper.deleteSpeakerById(id);
        return result;
    }

    @Override
    public int updateSpeaker(Speaker speaker) {

        int result = mapper.updateSpeaker(speaker);
        return result;

    }

    @Override
    public List<Speaker> selectAllSpeaker(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Speaker> speakers = mapper.selectSpeakerList();
        return speakers;
    }

    @Override
    public List<Speaker> selectAllSpeaker() {
        List<Speaker> speakers = mapper.selectSpeakerList();
        System.out.println(speakers);
        return speakers;

    }

    @Override
    public int addSpeaker(Speaker speaker) {
        int result = mapper.addSpeaker(speaker);
        System.out.println("addSpeaker: " + result);
        return result;
    }

    @Override
    public Speaker selectSpeakerById(int id) {
        Speaker speaker = mapper.selectSpeakerById(id);
        return speaker;
    }


}
