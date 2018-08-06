package com.datanese.wuye.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
@Data
public class AuditPO 
	{
	    Long id;
	    Integer communityId;
	    long userId;
	    Integer state;
	    String reason;
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    LocalDateTime createTime;
	    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	    LocalDateTime updateTime;
	    String comment;
	   
	    String urls;


	}

