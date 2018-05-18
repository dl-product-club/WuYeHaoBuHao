package com.datanese.wuye.mapper;

import com.datanese.wuye.po.AccountPO;
import com.datanese.wuye.po.UserPO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface UserMapper {
	
	@Select("SELECT * FROM users")
	@Results({
		@Result(property = "userSex",  column = "user_sex"),
		@Result(property = "nickName", column = "nick_name")
	})
	List<UserPO> getAll();
	
	@Select("SELECT * FROM users WHERE id = #{id}")
	@Results({
		@Result(property = "userSex",  column = "user_sex"),
		@Result(property = "nickName", column = "nick_name")
	})
	UserPO getOne(Long id);

	@Insert("INSERT INTO account(account_id, weixin_id, create_time) VALUES(#{accountId}, #{weixinId}, #{createTime})")
	void insertAccount(AccountPO user);


	@Insert("INSERT INTO user_base(account_id,account_name,gender,city,province,country) VALUES(#{accountId}, #{accountName}, #{gender},#{city},#{province},#{country})")
	void insertUser(UserPO user);


	@Update("UPDATE users SET userName=#{userName},nick_name=#{nickName} WHERE id =#{id}")
	void update(UserPO user);


	@Delete("DELETE FROM users WHERE id =#{id}")
	void delete(Long id);


	@Select("SELECT account_id FROM account WHERE weixin_id = #{weixinId}")
	@Results({
			@Result(property = "accountId",  column = "account_id")
	})
	AccountPO getUserAccountByWeixinId(String weixinId);

	@Update("UPDATE user_community SET community_id=#{communityId} WHERE user_id =#{userId}")
    void setUserDefaultCommunity(long userId, int communityId);
}