package com.course.server.domain;

import java.util.Date;

public class Comment {
    private String id;
    private String courseId;
    private String memberId;
    private String content;
    private Date createTime;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", courseId='" + courseId + '\'' +
                ", memberId='" + memberId + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", name='" + name + '\'' +
                '}';
    }
}
