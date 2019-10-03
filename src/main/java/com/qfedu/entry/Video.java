package com.qfedu.entry;

import com.qfedu.utils.StrTimeUtils;

public class Video {
    private int id;
    private String title;
    private String detail;
    private String speakerName;
    private int spearkerId;
    private int courseId;
    private int time;
    private String videoUrl;
    private String videoImageUrl;

    private String showTime;
    private int playNum;

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    private Speaker speaker;

    @Override
    public String toString() {
        return "video{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", detail='" + detail + '\'' +
                ", speakerName='" + speakerName + '\'' +
                ", spearkerId=" + spearkerId +
                ", courseId=" + courseId +
                ", time=" + time +
                ", videoUrl='" + videoUrl + '\'' +
                ", videoImageUrl='" + videoImageUrl + '\'' +
                ", showTime='" + showTime + '\'' +
                ", playNum=" + playNum +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSpeakerName() {
        return speakerName;
    }

    public void setSpeakerName(String speakerName) {
        this.speakerName = speakerName;
    }

    public int getSpearkerId() {
        return spearkerId;
    }

    public void setSpearkerId(int spearkerId) {
        this.spearkerId = spearkerId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoImageUrl() {
        return videoImageUrl;
    }

    public void setVideoImageUrl(String videoImageUrl) {
        this.videoImageUrl = videoImageUrl;
    }


    public String getShowTime() {
        return StrTimeUtils.intToStrTime(time);
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getPlayNum() {
        return playNum;
    }

    public void setPlayNum(int playNum) {
        this.playNum = playNum;
    }

    public Video() {
    }

    public Video(int id, String title, String detail, String speakerName, int spearkerId, int courseId, int time, String videoUrl, String videoImageUrl, String showTime, int playNum) {
        this.id = id;
        this.title = title;
        this.detail = detail;
        this.speakerName = speakerName;
        this.spearkerId = spearkerId;
        this.courseId = courseId;
        this.time = time;
        this.videoUrl = videoUrl;
        this.videoImageUrl = videoImageUrl;
        this.showTime = showTime;
        this.playNum = playNum;

    }
}
