package com.qfedu.controller;

import com.github.pagehelper.PageInfo;
import com.qfedu.entry.Speaker;
import com.qfedu.service.SpeakerService;
import com.qfedu.utils.InfoUtils;
import com.qfedu.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;


@Controller
@RequestMapping("/speaker")
public class SpeakerController {
@Autowired
SpeakerService speakerService;
    @RequestMapping("/list")
    public String list(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page, @RequestParam(name = "pageSize", required = false, defaultValue = "5") int pageSize) {

        List<Speaker> speakerList = speakerService.selectAllSpeaker(page, pageSize);
        PageInfo<Speaker> pageInfo = new PageInfo<>(speakerList);
        model.addAttribute("pageInfo", pageInfo);
        return "behind/speakerList";
    }

    @RequestMapping("/addSpeaker")
    public String addSpeaker(Model model) {
        List<Speaker> speakers = speakerService.selectAllSpeaker();
        model.addAttribute("speakerList", speakers);

        return "behind/addSpeaker";
    }

    @RequestMapping("/saveOrUpdate")
    @ResponseBody
    public String saveOrUpdate(Speaker speaker) {
        if (speaker.getId() != 0) {

            return "success";
        }
        speakerService.addSpeaker(speaker);

        return "success";
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

    @RequestMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {


        Speaker speaker = speakerService.selectSpeakerById(id);
        System.out.println(id);
        model.addAttribute("speaker", speaker);


        return "/behind/addSpeaker";

    }
    @RequestMapping("/speakerDel")
    @ResponseBody
    public String speakerDel(Integer id){
        return "success";
    }
}
