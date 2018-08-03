package com.datanese.wuye.dto;

import lombok.Data;

	/**
	 * Created by zhuojun.han on 3/8/2018.
	 */
	@Data
	public class AuditDTO {
	    Long id;
	    int communityId;
	    int state;
	    long userId;
	    String userImage;
	    String userNickName;
	    String createTime;
	    String updateTime;
	    String comment;
	    String reason;
	    String[] imageURL;
	}
