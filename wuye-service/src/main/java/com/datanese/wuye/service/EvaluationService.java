package com.datanese.wuye.service;

import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.CommunityPO;
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
    public List<CommunityPO> getAllResidentialDistrict(){
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

    public List<CommunityPO> search(String keyword) {
        return null;
    }

    public List<EvaluationPO> getAllEvaluation(Integer residentialDistrictId) {
        return null;
    }
}


