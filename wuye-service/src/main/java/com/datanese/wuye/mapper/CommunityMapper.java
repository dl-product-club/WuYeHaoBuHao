package com.datanese.wuye.mapper;

import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.po.WeixinAccountPO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface CommunityMapper {

	@Select("select * from community")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "name",  column = "name"),
			@Result(property = "image",  column = "image")
	})
	List<CommunityPO> getAll();
}