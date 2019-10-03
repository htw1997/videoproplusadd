package com.qfedu.controller;


import com.github.pagehelper.PageInfo;
import com.qfedu.entry.Course;
import com.qfedu.entry.Subject;
import com.qfedu.entry.Video;
import com.qfedu.service.CourseService;
import com.qfedu.service.SubjectService;
import com.qfedu.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/course")

public class CourseController {

    @Autowired
    CourseService courseService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    VideoService videoService;

    @RequestMapping("/list")
    public String list(Integer subjectId, Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize) {
        List<Course> courseList = courseService.selectAllCourse(page, pageSize);
        Subject subject = subjectService.getSubjectById(subjectId);
        PageInfo<Course> pageInfo = new PageInfo<>(courseList);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("subject", subject);
        return "behind/courseList";
    }

    @RequestMapping("/list1")
    public String list1(Integer subjectId, Model model) {
        Subject subject = subjectService.getSubjectById(subjectId);
        System.out.println(subjectId);
        List<Course> courseList = courseService.getCourseBySubjectId(subjectId);

        for (Course course : courseList) {
            List<Video> videoList = videoService.getVideoListByCourseId(course.getId());
            course.setVideoList(videoList);
        }
        subject.setCourseList(courseList);
        model.addAttribute("subject", subject);
        return "before/course";
    }

    @RequestMapping("/addCourse")
    public String addCourse(Model model) {
        List<Subject> subjectList = subjectService.subjectAll();
        model.addAttribute("subjectList", subjectList);
        List<Course> courses = courseService.selectAllCourse();
        model.addAttribute("courseList", courses);
        return "behind/addCourse";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Course course) {
        if (course.getId() != 0) {
            courseService.updateCourse(course);
            return "success";
        }
        courseService.addCourse(course);
        return "success";
    }

    @RequestMapping("/deleteCourseById")
    @ResponseBody
    public String deleteCourseById(Integer id) {
        return "success";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Course course = courseService.selectCourseById(id);
        model.addAttribute("course", course);

        return "behind/addCourse";

    }

}
