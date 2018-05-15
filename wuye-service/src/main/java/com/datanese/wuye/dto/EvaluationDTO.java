package com.datanese.wuye.dto;

import java.time.LocalDateTime;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
public class EvaluationDTO {
    Long id;
    int residentialDistrictId;
    int rate;
    LocalDateTime createTime;
    String comment;
    long user;
    String[] imageURL;

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getResidentialDistrictId() {
        return residentialDistrictId;
    }

    public void setResidentialDistrictId(Integer residentialDistrictId) {
        this.residentialDistrictId = residentialDistrictId;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }


    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public String[] getImageURL() {
        return imageURL;
    }

    public void setImageURL(String[] imageURL) {
        this.imageURL = imageURL;
    }
}
