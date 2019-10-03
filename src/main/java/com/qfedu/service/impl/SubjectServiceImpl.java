package com.qfedu.service.impl;

import com.qfedu.entry.Subject;
import com.qfedu.mapper.SubjectMapper;
import com.qfedu.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectMapper subjectMapper;
    @Override
    public Subject getSubjectById(Integer subjectId) {
        return  subjectMapper.getSubjectById(subjectId);

    }

    @Override
    public List<Subject> subjectAll() {
        List<Subject> subjectList = subjectMapper.subjectAll();

        return subjectList;
    }
}
