package cn.itcast.oa.domain;

import java.util.Date;

public class Article {
    private Long id;
    private String title; //标题
    private String content;//内容
    private String faceIcon;//表情符号

    private User author ;//作者
    private Date postTime;//发表时间
    private String ipAddr;//发表文章时所用的ip地址

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFaceIcon() {
        return faceIcon;
    }

    public void setFaceIcon(String faceIcon) {
        this.faceIcon = faceIcon;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
