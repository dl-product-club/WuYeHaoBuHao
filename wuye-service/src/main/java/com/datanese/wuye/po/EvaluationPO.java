package com.datanese.wuye.po;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
public class EvaluationPO {
    Long id;
    Integer residentialDistrictId;
    Integer rate;
    LocalDateTime createTime;
    String comment;
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
}
