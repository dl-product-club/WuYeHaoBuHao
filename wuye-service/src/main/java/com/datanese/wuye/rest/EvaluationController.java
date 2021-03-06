package com.datanese.wuye.rest;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.common.utils.IOUtils;
import com.datanese.wuye.Constant;
import com.datanese.wuye.dto.BadWordDTO;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.ImageDTO;
import com.datanese.wuye.dto.ResultDTO;
import com.datanese.wuye.exception.BadWordException;
import com.datanese.wuye.exception.SessionExpiredException;
import com.datanese.wuye.exception.UserNotExistException;
import com.datanese.wuye.po.EvaluationPO;
import com.datanese.wuye.service.AliyunOSSStorageService;
import com.datanese.wuye.service.EvaluationService;
import com.datanese.wuye.session.SessionEntity;
import com.datanese.wuye.util.SnowflakeIdWorker;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
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
    private RedisTemplate redisTemplate;
    @Autowired
    AliyunOSSStorageService aliyunOSSStorageService;

    @Autowired
    RestTemplate restTemplate;



    @GetMapping("/evaluationNumbers/{communityId}")
    public long[]  getEvaluationNumbers(@RequestHeader HttpHeaders headers, @PathVariable Integer communityId)throws Exception{
        long[] numbers = evaluationService.getEvaluationNumbers(communityId);
        return numbers;
    }

    @GetMapping("/evaluations/preview/{communityId}")
    public  List<EvaluationDTO> getPreviewEvaluation(@RequestHeader HttpHeaders headers, @PathVariable Integer communityId )throws Exception {
        //需要验证
//        String sessionId = headers.getFirst("sessionId");
//        if (StringUtils.isBlank(sessionId)) {
//            throw new SessionExpiredException();
//        }
//        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
//        if (se == null) {
//            // session 过期
//            throw new SessionExpiredException();
//        }
//        if (se.getUserId() <= 0) {
//            throw new UserNotExistException();
//        }
        List<EvaluationDTO> previewEvaluation = evaluationService.getPreviewEvaluation(communityId);
        return previewEvaluation;
    }

    @GetMapping("/evaluations/positive/{communityId}")
    public  List<EvaluationDTO> getAllGoodEvaluation(@RequestHeader HttpHeaders headers, @PageableDefault(page = 0, size = 10) Pageable pageable, @PathVariable Integer communityId) throws Exception{
        List<EvaluationDTO> allGoodEvaluation = evaluationService.getAllGoodEvaluation(pageable,communityId);
        return allGoodEvaluation;
    }

    @GetMapping("/evaluations/critical/{communityId}")
    public  List<EvaluationDTO> getAllBadEvaluation(@RequestHeader HttpHeaders headers,@PageableDefault(page = 0, size = 10) Pageable pageable,@PathVariable Integer communityId)throws Exception {
//        //需要验证
//        String sessionId = headers.getFirst("sessionId");
//        if (StringUtils.isBlank(sessionId)) {
//            throw new SessionExpiredException();
//        }
//        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
//        if (se == null) {
//            // session 过期
//            throw new SessionExpiredException();
//        }
//        if (se.getUserId() <= 0) {
//            throw new UserNotExistException();
//        }
        List<EvaluationDTO> allGoodEvaluation = evaluationService.getAllBadEvaluation(pageable,communityId);
        return allGoodEvaluation;
    }

    @PostMapping("/review")
    @ResponseBody
    public ResultDTO review(@RequestHeader HttpHeaders headers,@RequestBody EvaluationDTO evaluationDTO) throws Exception{
        //需要验证
        String sessionId = headers.getFirst("sessionId");
        if (StringUtils.isBlank(sessionId)) {
            throw new SessionExpiredException();
        }
        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
        if (se == null) {
            // session 过期
            throw new SessionExpiredException();
        }
        if (se.getUserId() <= 0) {
            throw new UserNotExistException();
        }
        //需要校验
        boolean ok=checkBadWords(evaluationDTO.getComment());
        if(!ok){
            //TODO 插入到Audit表中
            throw new BadWordException();
        }
        //根据headers 的 sessionid 验证用户有效性
        evaluationDTO.setUserId(se.getUserId());
        evaluationService.review(evaluationDTO);
        ResultDTO resultDTO=new ResultDTO();
        resultDTO.setResult(Constant.RESPONSE_RESULT_OK);
        return resultDTO;
    }

    @PostMapping("/checkBadWords")
    @ResponseBody
    public boolean checkBadWords(@RequestBody String words) {
        HttpEntity<String> requestEntity = new HttpEntity<String>(words);
        try {
            BadWordDTO result = restTemplate.postForEntity("https://api.datanese.com/badword/check", requestEntity, BadWordDTO.class).getBody();
            return result.getLegal();
        } catch (Exception e) {
            logger.error("check bad words error", e);
        }
        return true;
    }
}
