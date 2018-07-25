package com.rigger;

import com.rigger.config.ServiceInfoUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
//@EnableTransactionManagement
public class SpringBootRiggerApplication {
    private static final Log log = LogFactory.getLog(SpringBootRiggerApplication.class);

    public static void main(String[] args) {
//		SpringApplication.run(SpringBootRiggerApplication.class, args);
        SpringApplication app = new SpringApplication(SpringBootRiggerApplication.class);
        app.run(args);
        log.info("spring-boot-rigger is success!");
        System.err.println("sample started. http://localhost:"+ ServiceInfoUtil.getPort());
    }
}