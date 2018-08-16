package com.datanese.wuye.rest;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.common.utils.IOUtils;
import com.datanese.wuye.Constant;
import com.datanese.wuye.dto.AuditDTO;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.ImageDTO;
import com.datanese.wuye.dto.ResultDTO;
import com.datanese.wuye.exception.SessionExpiredException;
import com.datanese.wuye.exception.UserNotExistException;
import com.datanese.wuye.service.AliyunOSSStorageService;
import com.datanese.wuye.service.AuditService;
import com.datanese.wuye.session.SessionEntity;
import com.datanese.wuye.util.SnowflakeIdWorker;

/**
 * Created by zhuojun.han on 3/8/2018.
 */
@RestController
public class AuditController {
	 private Logger logger = LoggerFactory.getLogger(getClass());
	    @Autowired
	    private AuditService auditService;
	    @Autowired
	    private RedisTemplate redisTemplate;
	    @Autowired
	    AliyunOSSStorageService aliyunOSSStorageService;

	    @GetMapping("/audits/all/{communityId}")
	    public  List<AuditDTO> getAllAudit(@RequestHeader HttpHeaders headers, @PageableDefault(page = 0, size = 10) Pageable pageable, @PathVariable Integer communityId) throws Exception{
//	        //需要验证
//	        String sessionId = headers.getFirst("sessionId");
//	        if (StringUtils.isBlank(sessionId)) {
//	            throw new SessionExpiredException();
//	        }
//	        SessionEntity se = (SessionEntity) redisTemplate.opsForValue().get(sessionId);
//	        if (se == null) {
//	            // session 过期
//	            throw new SessionExpiredException();
//	        }
//	        if (se.getUserId() <= 0) {
//	            throw new UserNotExistException();
//	        }
	        List<AuditDTO> allAuditData = auditService.getAllAuditData(pageable,communityId);
	        return allAuditData;
	    }
	    	   
	   
	}
