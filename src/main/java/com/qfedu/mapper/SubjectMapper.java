package com.qfedu.mapper;

import com.qfedu.entry.Subject;

import java.util.List;

public interface SubjectMapper {

    Subject getSubjectById(Integer subjectId);

    List<Subject> subjectAll();
}
