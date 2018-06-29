package com.datanese.wuye.dto;

import java.time.LocalDateTime;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
public class EvaluationDTO {
    Long id;
    int communityId;
    int rate;
    LocalDateTime createTime;
    String comment;
    long userId;
    String[] imageURL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCommunityId() {
        return communityId;
    }

    public void setCommunityId(int communityId) {
        this.communityId = communityId;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String[] getImageURL() {
        return imageURL;
    }

    public void setImageURL(String[] imageURL) {
        this.imageURL = imageURL;
    }
}
