package com.qfedu.controller;

import com.qfedu.entry.Course;
import com.qfedu.entry.Speaker;
import com.qfedu.entry.Video;
import com.qfedu.entry.VideoQueryVo;
import com.qfedu.service.CourseService;
import com.qfedu.service.SpeakerService;
import com.qfedu.service.VideoService;
import com.qfedu.utils.InfoUtils;
import com.qfedu.utils.Page;
import com.qfedu.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/video")
public class VideoController {
    @Autowired
    VideoService videoService;
    @Autowired
    SpeakerService speakerService;
    @Autowired
    CourseService courseService;

    @RequestMapping("/list")
    public String showList(VideoQueryVo queryVo, Model model) {
        queryVo.setBegin((queryVo.getPage() - 1) * queryVo.getRows());
        List<Video> list = videoService.selectListByQueryVo(queryVo);
        List<Video> listAll = videoService.videoListAll(queryVo );
        Page<Video> page = new Page<Video>();
        page.setTotal(listAll.size());
        page.setPage(queryVo.getPage());
        page.setSize(queryVo.getRows());
        page.setRows(list);
        model.addAttribute("page", page);

        List<Speaker> speakers = speakerService.selectAllSpeaker();
        System.out.println(speakers);
        List<Course> courses = courseService.getCourseList();
        model.addAttribute("courseList", courses);
        model.addAttribute("speakerList", speakers);

        return "behind/videoList";
    }

    @RequestMapping("/addVideo")
    public String addVideo(Model model) {
        List<Speaker> speakers = speakerService.selectAllSpeaker();
        model.addAttribute("speakerList", speakers);
        List<Course> courses = courseService.getCourseList();
        model.addAttribute("courseList", courses);
        return "behind/addVideo";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Video video) {
        if (video.getId() != 0) {
            videoService.updateVideos(video);
            System.out.println("进去了");
            return "success";

        }
        videoService.saveVideo(video);
        //System.out.println("nitamde:" + video);

        return "success";
    }

    @RequestMapping("/showVideo")
    public String showVideo(Integer videoId, String subjectName, Model model) {

        Video video = videoService.getVideoById(videoId);

        model.addAttribute("subjectName", subjectName);
        Speaker speaker = speakerService.selectSpeakerById(video.getSpearkerId());
        video.setSpeaker(speaker);
        model.addAttribute("video", video);

        int courseId = video.getCourseId();
        System.out.println(courseId);
        Course course = courseService.selectCourseById(courseId);
        System.out.println(course);
        List<Video> videoList = videoService.getVideoListByCourseId(video.getCourseId());

        model.addAttribute("course", course);

        return "before/section";
    }

    @RequestMapping("/upload")
    @ResponseBody
    //上传图片
    public String uploadImg(MultipartFile headImg) {
        System.out.println(headImg.getOriginalFilename());

        String oldName = headImg.getOriginalFilename();
        String suffix = oldName.substring(oldName.lastIndexOf("."));
        String newFileName = UUIDUtils.getUUID() + suffix;

        try {
            //完成上传功能
            headImg.transferTo(new File(InfoUtils.getProperties("IMG_PATH"), newFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String imgUrl = InfoUtils.getProperties("IMG_URL") + newFileName;
        return imgUrl;
    }

    @RequestMapping("/videoDel")
    @ResponseBody
    public String videoDel(Integer id) {
        return "success";
    }

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        System.out.println("id =" + id);
        Video video = videoService.selectVideoById(id);
        model.addAttribute("video", video);
        List<Speaker> speakers = speakerService.selectAllSpeaker();
        model.addAttribute("speakerList", speakers);
        List<Course> courseList = courseService.getCourseList();
        model.addAttribute("courseList" + courseList);
        return "/behind/addVideo";
    }


    @RequestMapping("/delBatchVideos")
    public String delBatchVideos(Integer[] ids) {
        System.out.println(Arrays.toString(ids));
        int result = videoService.delBatchVideos(ids);
        System.out.println("删除：" + result);
        return "redirect:/video/list";
    }

    @RequestMapping("/updatePalyNum")
    @ResponseBody
    public void updatePalyNum(Integer videoId, Integer playNum) {
        Video video = new Video();
        video.setId(videoId);
        video.setPlayNum(playNum+1);
        System.out.println(video);
        videoService.addPlayNum(video);
    }
}

