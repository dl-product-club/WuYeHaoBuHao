package com.datanese.wuye;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
public class BeanConfiguration {
    @Autowired
    OSSProperties ossPropertiesConfig;

    @Bean
    public OSSClient ossClient(){
        OSSClient ossClient = new OSSClient(ossPropertiesConfig.getEndpoint(), ossPropertiesConfig.getAccessKeyId(), ossPropertiesConfig.getAccessKeySecret());
        return ossClient;
    }




}
