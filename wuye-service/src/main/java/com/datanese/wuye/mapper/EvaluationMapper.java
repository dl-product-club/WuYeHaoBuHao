package com.datanese.wuye.mapper;

import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.po.AccountPO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.ResidentialDistrictPO;
import com.datanese.wuye.po.UserPO;
import org.apache.ibatis.annotations.*;

import java.util.List;


public interface EvaluationMapper {
	
	@Select("SELECT * FROM residential_district")
	@Results({
		@Result(property = "id",  column = "id"),
		@Result(property = "name", column = "name")
	})
	List<ResidentialDistrictPO> getAllResidentialDistrict();


	@Select("SELECT * FROM evaluation WHERE residential_district = #{residentialDistrictId} and rate>0")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "rate", column = "rate"),
			@Result(property = "comment", column = "comment"),
			@Result(property = "residentialDistrictId", column = "residential_district"),
			@Result(property = "createTime", column = "create_time")
	})
	 List<EvaluationPO> getAllGoodEvaluation(Integer residentialDistrictId);


	@Select("SELECT * FROM evaluation WHERE residential_district = #{residentialDistrictId} and rate<0")
	@Results({
			@Result(property = "id",  column = "id"),
			@Result(property = "rate", column = "rate"),
			@Result(property = "comment", column = "comment"),
			@Result(property = "residentialDistrictId", column = "residential_district")
	})
	List<EvaluationPO> getAllBadEvaluation(Integer residentialDistrictId);

	@Insert("INSERT INTO evaluation(rate,comment,residential_district) VALUES(#{rate}, #{comment}, #{residentialDistrictId})")
	void insertEvaluation(EvaluationPO evaluationDTO);

}