package com.datanese.wuye.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.datanese.wuye.po.AuditPO;


public interface AuditMapper {
	@Select("SELECT * FROM Audit WHERE community_id = #{communityId} and state=2")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "state", column = "state"),
			@Result(property = "comment", column = "comment"),
			@Result(property="reason",column="reason"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "communityId", column = "community_id"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "updateTime", column = "update_time"),
            @Result(property = "urls", column = "urls")
	})
	 List<AuditPO> getAllAudit(Integer communityId);
}
