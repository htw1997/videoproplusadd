package com.qfedu.service;

import com.qfedu.entry.Subject;

import java.util.List;

public interface SubjectService {
    Subject getSubjectById(Integer subjectId);

    List<Subject> subjectAll();
}
