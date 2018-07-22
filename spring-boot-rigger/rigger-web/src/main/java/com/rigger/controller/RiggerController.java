package com.rigger.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController()
@RequestMapping("/rigger")
public class RiggerController {
    private Log log = LogFactory.getLog(RiggerController.class);


    @GetMapping("/info")
    public String info() {
        log.info("log....");
        return "Hello spring-boot-rigger";
    }

    @GetMapping("/fail")
    public String fail() {
        log.info("fail....");
        if(true) {
            throw new NullPointerException();
            //throw new RuntimeException("异常游戏，异常梦");
        }
        int i=1/0;
        return "Hello spring-boot-rigger";
    }


    @GetMapping("req")
    public void req(HttpServletRequest request, @CookieValue(value="JESSIONID",required = false) String sessionId){
        System.out.println("sessionId="+request.getSession().getId());
        System.out.println("sessionId="+sessionId);
    }


}
