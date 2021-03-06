package com.mooc.business.config;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication//can scan all packages in current directory
@EnableEurekaClient
@MapperScan("com.mooc.server.mapper")
@ComponentScan("com.mooc")
public class BusinessApplication {

//	public static void main(String[] args) {
//		SpringApplication.run(EurekaApplication.class, args);
//	}

    private static final Logger LOG = LoggerFactory.getLogger(BusinessApplication.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(BusinessApplication.class);
        Environment env = app.run(args).getEnvironment();
        LOG.info("boot successful！！");
        LOG.info("Business address: \thttp://127.0.0.1:{}", env.getProperty("server.port"));
    }
}
