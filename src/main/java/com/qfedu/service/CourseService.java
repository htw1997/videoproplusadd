package com.qfedu.service;

import com.qfedu.entry.Course;

import java.util.List;

public interface CourseService {
    List<Course> getCourseList();

    List<Course> selectAllCourse(int page, int pageSize);

    List<Course> selectAllCourse();

    int addCourse(Course course);

    int updateCourse(Course course);

    int delBatchCourse(Integer id);

    Course selectCourseById(int id);

    List<Course> getCourseBySubjectId(Integer subjectId);
}
