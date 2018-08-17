package com.datanese.wuye.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.IOUtils;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.PutObjectResult;
import com.datanese.wuye.OSSProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
    private OSSClient ossClient;

    public AliyunOSSStorageService() {
    }

    public void put(String fileName, InputStream inputStream) {
        PutObjectResult result = ossClient.putObject(ossPropertiesConfig.getBucketName(), fileName, inputStream);
    }

    public InputStream get(String fileName) {
        InputStream inputStream = null;
        try {
            OSSObject object = ossClient.getObject(ossPropertiesConfig.getBucketName(), fileName);
            inputStream = object.getObjectContent();
            return inputStream;
        } catch (Exception ce) {
            logger.error("store to OSS error", ce);
            throw ce;
        } finally {
//            IOUtils.safeClose(inputStream);
//            ossClient.shutdown();
        }
    }

    public byte[]  getBytes(String fileName) {
        InputStream inputStream = null;
        try {
            OSSObject object = ossClient.getObject(ossPropertiesConfig.getBucketName(), fileName);
            inputStream = object.getObjectContent();
            return input2byte(inputStream);
        } catch (IOException ce) {
            logger.error("store to OSS error", ce);
        } finally {
            IOUtils.safeClose(inputStream);
//            ossClient.shutdown();
        }
        return null;
    }

    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[1000];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 1000)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }
}
