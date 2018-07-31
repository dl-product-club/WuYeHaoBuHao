package com.datanese.wuye.mapper;

import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.po.WeixinAccountPO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {
	
	@Select("SELECT * FROM users")
	@Results({
		@Result(property = "userSex",  column = "user_sex"),
		@Result(property = "nickName", column = "nick_name")
	})
	List<UserPO> getAll();
	
	@Select("SELECT * FROM user WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex"),
		@Result(property = "nickName", column = "nick_name")
	})
	UserPO getOne(Long id);

	@Insert("INSERT INTO weixin_account(user_id, open_id,nick_name,gender,language,city,province,country,avatarUrl,unionId) VALUES(#{userId}, #{openid},#{nickname},#{gender},#{language},#{city},#{province},#{country},#{avatarUrl},#{unionid})")
	void insertWeixinAccount(WeixinAccountPO user);


	@Insert("INSERT INTO user(id,name,gender,avatarUrl) VALUES(#{id}, #{name}, #{gender},#{avatarUrl})")
	void insertUser(UserPO user);


	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(UserPO user);


//	@Delete("DELETE FROM users WHERE id =#{id}")
//	void delete(Long id);


	@Select("SELECT user_id FROM weixin_account WHERE open_id = #{weixinId}")
	@Results({
			@Result(property = "id",  column = "user_id")
	})
	UserPO getUserByWeixinId(String weixinId);

	@Insert("replace into user_default_community (user_id,community_id) values(#{userId},#{communityId})")
    void setUserDefaultCommunity(@Param("userId")long userId, @Param("communityId")int communityId);

	@Select("select community.* from community,user_default_community where user_default_community.user_id=#{userId} and user_default_community.community_id=community.id")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "name",  column = "name"),
			@Result(property = "image",  column = "image")
	})
	CommunityDTO getUserDefaultCommunity(long userId);
}