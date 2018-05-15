package com.datanese.wuye.rest;

import com.aliyun.oss.common.utils.IOUtils;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.ImageDTO;
import com.datanese.wuye.dto.WeixinAccountDTO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.ResidentialDistrictPO;
import com.datanese.wuye.service.AliyunOSSStorageService;
import com.datanese.wuye.service.EvaluationService;
import com.datanese.wuye.service.UserService;
import com.datanese.wuye.util.SnowflakeIdWorker;
import com.sun.imageio.plugins.common.ImageUtil;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
@RestController
public class EvaluationController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private EvaluationService evaluationService;

    @Autowired
    AliyunOSSStorageService aliyunOSSStorageService;

    @GetMapping("/residentialDistricts")
    public List<ResidentialDistrictPO> getAllResidentialDistrict(@RequestParam String keyword) {
        if(StringUtils.isEmpty(keyword)){
            List<ResidentialDistrictPO> allResidentialDistrict = evaluationService.getAllResidentialDistrict();
            return allResidentialDistrict;
        }
        return evaluationService.search(keyword);

    }

    @GetMapping("/evaluations/{residentialDistrictId}")
    public  List<EvaluationPO> getAllEvaluation(@PathVariable Integer residentialDistrictId) {
        List<EvaluationPO> allGoodEvaluation = evaluationService.getAllEvaluation(residentialDistrictId);
        return allGoodEvaluation;
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
    public void review(@RequestBody EvaluationDTO evaluationDTO) {
        evaluationService.review(evaluationDTO);
    }

    @PostMapping("/image/upload")
    public ImageDTO uploadImg(@RequestParam("image") MultipartFile multipartFile)  {
        ImageDTO imageDTO=new ImageDTO();
        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            imageDTO.setResult("fail");
            imageDTO.setMessage("image is empty");
            return imageDTO;
        }
        InputStream inputStream=null;
        try {
            inputStream=multipartFile.getInputStream();
            String fileName= SnowflakeIdWorker.nextId()+".png";
            aliyunOSSStorageService.put(fileName,inputStream);
            imageDTO.setResult("success");
            imageDTO.setMessage("upload image successfully");
            imageDTO.setUrl("/image/"+fileName);
        } catch (IOException e) {
            logger.error("store to OSS error",e);
            imageDTO.setResult("fail");
            imageDTO.setMessage("upload image failed");
        }finally {
            IOUtils.safeClose(inputStream);
        }
        return imageDTO;
    }
    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getImg(@PathVariable String filename)  {
        InputStream is=null;
        try {
            is=aliyunOSSStorageService.get(filename);
            return ResponseEntity.ok(new InputStreamResource(is));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }finally {
            //IOUtils.safeClose(is);
        }
    }
}
