package com.datanese.wuye.rest;

import com.aliyun.oss.common.utils.IOUtils;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.ImageDTO;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.po.CommunityPO;
import com.datanese.wuye.service.AliyunOSSStorageService;
import com.datanese.wuye.service.EvaluationService;
import com.datanese.wuye.util.SnowflakeIdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
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

    @GetMapping("/evaluations/all/{communityId}")
    public  List<EvaluationPO> getAllEvaluation(@PathVariable Integer communityId ) {
        List<EvaluationPO> allGoodEvaluation = evaluationService.getAllEvaluation(communityId);
        return allGoodEvaluation;
    }

    @GetMapping("/evaluations/positive/{communityId}")
    public  List<EvaluationDTO> getAllGoodEvaluation(@PathVariable Integer communityId) {
        List<EvaluationDTO> allGoodEvaluation = evaluationService.getAllGoodEvaluation(communityId);
        return allGoodEvaluation;
    }

    @GetMapping("/evaluations/critical/{communityId}")
    public  List<EvaluationDTO> getAllBadEvaluation(@PathVariable Integer communityId) {
        List<EvaluationDTO> allGoodEvaluation = evaluationService.getAllBadEvaluation(communityId);
        return allGoodEvaluation;
    }

    @PostMapping("/review")
    public void review(@RequestBody EvaluationDTO evaluationDTO) {
        //需要校验
        evaluationService.review(evaluationDTO);
    }

    @PostMapping("/image/upload")
    public ImageDTO uploadImg(@RequestParam("image") MultipartFile multipartFile)  {
        //需要校验
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
