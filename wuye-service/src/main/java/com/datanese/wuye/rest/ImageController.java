package com.datanese.wuye.rest;

import com.aliyun.oss.common.utils.IOUtils;
import com.datanese.wuye.Constant;
import com.datanese.wuye.dto.EvaluationDTO;
import com.datanese.wuye.dto.ImageDTO;
import com.datanese.wuye.dto.ResultDTO;
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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by bing.a.qian on 9/10/2017.
 */
@RestController
public class ImageController {
    private static String IMAGE_PATH = "https://wuye.qianyitian.com/image/";
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AliyunOSSStorageService aliyunOSSStorageService;

    @PostMapping("/image/upload")
    public ImageDTO uploadImg(@RequestParam("image") MultipartFile multipartFile) {
        //需要校验
        ImageDTO imageDTO = new ImageDTO();
        if (multipartFile.isEmpty() || StringUtils.isEmpty(multipartFile.getOriginalFilename())) {
            imageDTO.setResult("fail");
            imageDTO.setMessage("image is empty");
            return imageDTO;
        }
        InputStream inputStream = null;
        try {
            inputStream = multipartFile.getInputStream();
            String fileName = SnowflakeIdWorker.nextId() + ".png";
            aliyunOSSStorageService.put(fileName, inputStream);
            imageDTO.setResult("success");
            imageDTO.setMessage("upload image successfully");
            imageDTO.setUrl(IMAGE_PATH + fileName);
        } catch (IOException e) {
            logger.error("store to OSS error", e);
            imageDTO.setResult("fail");
            imageDTO.setMessage("upload image failed");
        } finally {
            IOUtils.safeClose(inputStream);
        }
        return imageDTO;
    }

    @GetMapping("/image/{filename:.+}")
    @ResponseBody
    public ResponseEntity<?> getImg(@PathVariable String filename) {
        InputStream is = null;
        try {
            is = aliyunOSSStorageService.get(filename);
            return ResponseEntity.ok(new InputStreamResource(is));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        } finally {
            //IOUtils.safeClose(is);
        }
    }
}
