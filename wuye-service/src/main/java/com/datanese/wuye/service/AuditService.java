package com.datanese.wuye.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import com.datanese.wuye.dto.AuditDTO;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.mapper.AuditMapper;
import com.datanese.wuye.po.AuditPO;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.po.EvaluationPO;
import com.google.common.base.Splitter;
@Service
public class AuditService {
	 @Autowired
	    private AuditMapper auditMapper;
	    public List<AuditDTO> getAllAuditData(Pageable pageable, Integer communityId) {
	    	  Page<EvaluationPO> page=new Page<EvaluationPO>(pageable.getPageNumber(),pageable.getPageSize());
	    	   List<AuditPO> poList=auditMapper.getAllAudit(page,communityId);
	           List<AuditDTO> dtoList=new LinkedList();
	           toDTO(poList, dtoList);
	           return dtoList;
	           
	    }

	    private void toDTO(List<AuditPO> poList, List<AuditDTO> dtoList) {
	        for (AuditPO auditPO:poList)
	        {
	            String poString = JSON.toJSONString(auditPO);
	            AuditDTO auditDTO = JSON.parseObject(poString, AuditDTO.class);
	            List<String> result = Splitter.on(",").trimResults().splitToList(auditPO.getUrls());
	            auditDTO.setImageURL(result.toArray(new String[result.size()]));	            
	            dtoList.add(auditDTO);
	        }
	    }	   
}