package com;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.rigger")
public class SpringBootRiggerApplication {
    private static final Log log = LogFactory.getLog(SpringBootRiggerApplication.class);

    public static void main(String[] args) {
//		SpringApplication.run(SpringBootRiggerApplication.class, args);
        SpringApplication app = new SpringApplication(SpringBootRiggerApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
        log.info("spring-boot-rigger is success!");
        System.err.println("sample started. http://localhost:8080/hello/rigger");
    }
}
