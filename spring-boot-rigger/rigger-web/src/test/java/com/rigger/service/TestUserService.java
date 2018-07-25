package com.rigger.service;

import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.rigger.SpringBootRiggerApplication;
import com.rigger.controller.UserController;
import com.rigger.model.User;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ActiveProfiles(profiles = "dev")
@SpringBootTest(classes = SpringBootRiggerApplication.class)
public class TestUserService {

    @Autowired
    private UserService userService;

    @Test
    public void testTx(){
        userService.insert();
    }

    @Test
    public void testUpdate(){
        User user = userService.selectById(1);
        user.setAge(12);
        userService.updateById(user);
    }

    @Test
    public void testInsert() {
        User u = new User();
        u.setName("AA");
        userService.insert(u);
        System.out.println(u.getId());
    }


    @Test
    public void contextLoads() throws Exception {
        System.out.println(userService);
    }
}
