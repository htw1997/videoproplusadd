package com.qfedu.mapper;

import com.qfedu.entry.Course;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;

import java.util.List;

public interface CourseMapper {

    List<Course> selectAllCourse();

    int addCourse(Course course);

    int updateCourse(Course course);
    
    int delBatchCourse(Integer id);

    Course selectCourseById(int id);

    List<Course> getCourseBySubjectId(Integer subjectId);

    List<Course> courseAll();

}
