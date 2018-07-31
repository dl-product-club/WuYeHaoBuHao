package com.datanese.wuye.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
@Data
public class EvaluationDTO {
    Long id;
    int communityId;
    int rate;
    long userId;
    String userImage;
    String userNickName;
    String createTime;
    String comment;
    String[] imageURL;
}
