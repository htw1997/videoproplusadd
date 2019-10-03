package com.qfedu.service.impl;

import com.github.pagehelper.PageHelper;
import com.qfedu.entry.Course;
import com.qfedu.mapper.CourseMapper;
import com.qfedu.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

@Autowired
CourseMapper courseMapper;
    @Override
    public List<Course> getCourseList() {
        return courseMapper.courseAll();

    }

    @Override
    public List<Course> selectAllCourse(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Course> courseList = courseMapper.selectAllCourse();
        return courseList;
    }

    @Override
    public List<Course> selectAllCourse() {
        List<Course> courseList = courseMapper.selectAllCourse();
        return courseList;
    }

    @Override
    public int addCourse(Course course) {

        int result = courseMapper.addCourse(course);
        System.out.println("addCourse：" + result);
        return result;
    }

    @Override
    public int updateCourse(Course course) {
        int result = courseMapper.updateCourse(course);
        return result;
    }

    @Override
    public int delBatchCourse(Integer id) {
        int reusult = courseMapper.delBatchCourse(id);
        return reusult;
    }

    @Override
    public Course selectCourseById(int id) {
        Course course = courseMapper.selectCourseById(id);
        return course;
    }

    @Override
    public List<Course> getCourseBySubjectId(Integer subjectId) {
        List<Course>course  = courseMapper.getCourseBySubjectId(subjectId);
        return course;
    }

}
