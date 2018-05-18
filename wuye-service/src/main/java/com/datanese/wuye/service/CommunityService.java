package com.datanese.wuye.service;

import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.po.EvaluationPO;
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
    public List<CommunityDTO> getAllCommunities() {
        return null;
    }


    public List<CommunityDTO> searchCommunities(String keyword) {
        return null;
    }


    public CommunityDTO getUserDefaultCommunity(long userId) {
        return null;
    }
}


