package com.datanese.wuye.service;

import com.alibaba.fastjson.JSON;
import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.mapper.CommunityMapper;
import com.datanese.wuye.mapper.UserMapper;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.po.EvaluationPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Created by bing.a.qian on 9/8/2017.
 */
@Service
public class CommunityService {
    @Autowired
    private CommunityMapper communityMapper;
    public List<CommunityDTO> getAllCommunities() {
        List<CommunityPO> list= communityMapper.getAll();
        String userStr = JSON.toJSONString(list);
        List<CommunityDTO> dtoList = JSON.parseArray(userStr, CommunityDTO.class);
        return dtoList;
    }


    public List<CommunityDTO> searchCommunities(String keyword) {
        return null;
    }



}


