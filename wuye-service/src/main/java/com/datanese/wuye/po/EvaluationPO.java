package com.datanese.wuye.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
@Data
public class EvaluationPO {
    Long id;
    Integer communityId;
    long userId;
    Integer rate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime createTime;
    String comment;
    String urls;


}
