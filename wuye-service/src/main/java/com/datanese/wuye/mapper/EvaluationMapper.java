package com.datanese.wuye.mapper;

import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.CommunityPO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface EvaluationMapper {
	@Select("SELECT * FROM evaluation WHERE community_id = #{communityId} and rate>0")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "rate", column = "rate"),
			@Result(property = "comment", column = "comment"),
			@Result(property = "communityId", column = "community_id"),
			@Result(property = "createTime", column = "create_time"),
            @Result(property = "urls", column = "urls")
	})
	 List<EvaluationPO> getAllGoodEvaluation(Integer communityId);


	@Select("SELECT * FROM evaluation WHERE community_id = #{communityId} and rate<0")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "rate", column = "rate"),
            @Result(property = "comment", column = "comment"),
            @Result(property = "communityId", column = "community_id"),
            @Result(property = "createTime", column = "create_time"),
            @Result(property = "urls", column = "urls")
    })
	List<EvaluationPO> getAllBadEvaluation(Integer residentialDistrictId);

	@Insert("INSERT INTO evaluation(id,community_id,user_id,rate,comment,urls) VALUES(#{id},#{communityId},#{userId},#{rate}, #{comment}, #{urls})")
	void insertEvaluation(EvaluationPO evaluationDTO);
}