package com.datanese.wuye;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
