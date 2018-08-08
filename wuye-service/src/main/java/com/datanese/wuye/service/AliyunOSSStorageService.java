package com.datanese.wuye.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.datanese.wuye.OSSProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * This sample demonstrates how to upload/download an object to/from
 * Aliyun OSS using the OSS SDK for Java.
 */
@Service
public class AliyunOSSStorageService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    OSSProperties ossPropertiesConfig;

    @Autowired
    private  OSSClient ossClient;
    public AliyunOSSStorageService() {
    }
    public void put(String fileName, InputStream inputStream)  {
        PutObjectResult result= ossClient.putObject(ossPropertiesConfig.getBucketName(), fileName, inputStream);
    }

    public InputStream get(String fileName) {
        InputStream inputStream=null;
        try {
            OSSObject object = ossClient.getObject(ossPropertiesConfig.getBucketName(), fileName);
            inputStream = object.getObjectContent();
            return inputStream;
        } catch (Exception ce) {
            logger.error("store to OSS error",ce);
            throw ce;
        } finally {
//            IOUtils.safeClose(inputStream);
//            ossClient.shutdown();
        }
    }
}
