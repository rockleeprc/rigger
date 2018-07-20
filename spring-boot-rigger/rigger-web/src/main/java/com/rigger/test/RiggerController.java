package com.rigger.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class RiggerController {
    private Log log = LogFactory.getLog(RiggerController.class);


    @GetMapping("/rigger")
    public String info() {
        log.info("log....");
        return "Hello spring-boot-rigger";
    }


}
