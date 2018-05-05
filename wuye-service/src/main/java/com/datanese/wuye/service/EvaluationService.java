package com.datanese.wuye.service;

import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.WeixinAccountDTO;
import com.datanese.wuye.po.AccountPO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.ResidentialDistrictPO;
import com.datanese.wuye.po.UserPO;
import com.datanese.wuye.mapper.UserMapper;
import com.datanese.wuye.util.SnowflakeIdWorker;
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
public class EvaluationService {
    public List<ResidentialDistrictPO> getAllResidentialDistrict(){
        return null;
    }
    public  List<EvaluationPO>  getAllGoodEvaluation(Integer residentialDistrictId){
        return null;
    }

    @GetMapping("/evaluations/{residentialDistrictId}/critical")
    public  List<EvaluationPO> getAllBadEvaluation(@PathVariable Integer residentialDistrictId) {
        return null;
    }

    @PostMapping("/review")
    public void review(@RequestBody EvaluationDTO user) {

    }
}


