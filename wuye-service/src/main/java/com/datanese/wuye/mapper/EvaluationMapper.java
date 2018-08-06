package com.datanese.wuye.mapper;

import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.CommunityPO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface EvaluationMapper {
	@Select("SELECT evaluation.*, user.name,user.avatarUrl FROM evaluation,user WHERE community_id = #{communityId} and rate=1 and evaluation.user_id=user.id")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "rate", column = "rate"),
			@Result(property = "comment", column = "comment"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "communityId", column = "community_id"),
			@Result(property = "createTime", column = "create_time"),
            @Result(property = "urls", column = "urls"),
			@Result(property = "userImage",column="avatarUrl"),
			@Result(property = "userNickName",column="name")
	})
	 List<EvaluationPO> getAllGoodEvaluation(Integer communityId);


	@Select("SELECT evaluation.*, user.name,user.avatarUrl FROM evaluation,user WHERE community_id = #{communityId} and rate=-1 and evaluation.user_id=user.id")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "rate", column = "rate"),
			@Result(property = "comment", column = "comment"),
			@Result(property = "userId", column = "user_id"),
			@Result(property = "communityId", column = "community_id"),
			@Result(property = "createTime", column = "create_time"),
			@Result(property = "urls", column = "urls"),
			@Result(property = "userImage",column="avatarUrl"),
			@Result(property = "userNickName",column="name")
	})
	List<EvaluationPO> getAllBadEvaluation(Integer communityId);

	@Insert("INSERT INTO evaluation(id,community_id,user_id,rate,comment,urls) VALUES(#{id},#{communityId},#{userId},#{rate}, #{comment}, #{urls})")
	void insertEvaluation(EvaluationPO evaluationDTO);

	@Select("select count(*) from evaluation where community_id=#{communityId} and rate= #{rate}")
	Long getEvaluationNumber(@Param("communityId")Integer communityId, @Param("rate")Integer rate);

}