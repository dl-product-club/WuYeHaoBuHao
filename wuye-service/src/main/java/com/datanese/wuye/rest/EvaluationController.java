package com.datanese.wuye.rest;

import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.WeixinAccountDTO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.ResidentialDistrictPO;
import com.datanese.wuye.service.EvaluationService;
import com.datanese.wuye.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
@RestController
public class EvaluationController {

    @Autowired
    private EvaluationService evaluationService;

    @GetMapping("/residentialDistricts")
    public List<ResidentialDistrictPO> getAllResidentialDistrict() {
        List<ResidentialDistrictPO> allResidentialDistrict = evaluationService.getAllResidentialDistrict();
        return allResidentialDistrict;
    }

    @GetMapping("/evaluations/{residentialDistrictId}/positive")
    public  List<EvaluationPO> getAllGoodEvaluation(@PathVariable Integer residentialDistrictId) {
        List<EvaluationPO> allGoodEvaluation = evaluationService.getAllGoodEvaluation(residentialDistrictId);
        return allGoodEvaluation;
    }

    @GetMapping("/evaluations/{residentialDistrictId}/critical")
    public  List<EvaluationPO> getAllBadEvaluation(@PathVariable Integer residentialDistrictId) {
        List<EvaluationPO> allGoodEvaluation = evaluationService.getAllBadEvaluation(residentialDistrictId);
        return allGoodEvaluation;
    }

    @PostMapping("/review")
    public void review(@RequestBody EvaluationDTO user) {
        evaluationService.review(user);
    }
}
