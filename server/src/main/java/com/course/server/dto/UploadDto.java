package com.course.server.dto;


public class UploadDto {

    /**
     * id
     */
    private String id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 截图
     */
    private String file;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "UploadDto{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", content='" + content + '\'' +
                ", file='" + file + '\'' +
                '}';
    }
}