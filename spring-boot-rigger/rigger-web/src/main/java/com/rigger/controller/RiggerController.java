package com.rigger.controller;

import com.rigger.common.Result;
import com.rigger.util.JwtToken;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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


    @GetMapping("/session")
    public void session(HttpServletRequest request, @CookieValue(value="JESSIONID",required = false) String sessionId){
        System.out.println("sessionId="+request.getSession().getId());
        System.out.println("sessionId="+sessionId);
    }

    @GetMapping("/jwt")
    public Result<Map<String,String>> jwt(@RequestParam("userId") Long userId){
        String token = JwtToken.createToken(userId);
        Map<String,String> result = new HashMap<String,String>();
        result.put("token",token);
        return Result.ok(result);
    }


}
