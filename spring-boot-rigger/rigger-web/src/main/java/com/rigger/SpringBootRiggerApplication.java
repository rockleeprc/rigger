package com.rigger;

import com.rigger.config.EnvConfig;
import com.rigger.config.EnvironmentHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
//@EnableTransactionManagement
public class SpringBootRiggerApplication {
    private static final Log logger = LogFactory.getLog(SpringBootRiggerApplication.class);
    @Autowired
    private static EnvConfig env;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRiggerApplication.class, args);
        logger.info("spring-boot-rigger Is Success!");
        logger.debug("Druid Monitor. http://" + EnvironmentHolder.getAddr() + ":" + EnvironmentHolder.getPort() + "/druid/index.html");
        logger.debug("Sample Started. http://" + EnvironmentHolder.getAddr() + ":" + EnvironmentHolder.getPort());
        System.err.println("---Don't Use Prod Profile In Your Local Envrionment---");
    }
}