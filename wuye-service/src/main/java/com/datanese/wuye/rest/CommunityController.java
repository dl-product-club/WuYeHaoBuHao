package com.datanese.wuye.rest;

import com.datanese.wuye.dto.CommunityDTO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.service.AliyunOSSStorageService;
import com.datanese.wuye.service.CommunityService;
import com.datanese.wuye.service.EvaluationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
@RestController
public class CommunityController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CommunityService communityService;

    @Autowired
    AliyunOSSStorageService aliyunOSSStorageService;

    @GetMapping("/communities")
    public List<CommunityDTO> getAllResidentialDistrict(@RequestParam(required=false) String keyword) {
        if(StringUtils.isEmpty(keyword)){
            List<CommunityDTO> communityList = communityService.getAllCommunities();
            return communityList;
        }
        return communityService.searchCommunities(keyword);
    }





}
