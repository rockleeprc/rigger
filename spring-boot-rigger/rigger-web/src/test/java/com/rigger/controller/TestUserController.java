package com.rigger.controller;

import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.rigger.SpringBootRiggerApplication;
import com.rigger.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "prod")
@SpringBootTest(classes = SpringBootRiggerApplication.class)
public class TestUserController {
    private static final String URL = "http://localhost:8080/user";
    @Autowired
    private UserController userController;

    @Test
    public void selectById(){
        User user = userController.selectById(5);
        System.out.println(user);
    }

    @Test
    public void api() {
        ApiResult<String> api = userController.api("api");
        System.out.println(api.getData());
    }

    @Test
    public void contextLoads() throws Exception {
    }
}
