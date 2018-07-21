package com.rigger.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiAssert;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rigger.model.User;
import com.rigger.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 代码生成器，参考源码测试用例：
 * <p>
 * /mybatis-plus/src/test/java/com/baomidou/mybatisplus/test/generator/MysqlGenerator.java
 */
@RestController
@RequestMapping("/user")
public class UserController extends ApiController {

    @Autowired
    private IUserService userService;

    @GetMapping("/findById")
    public User findAll(Integer id) {
        User user = userService.selectById(id);
        return user;
    }


    /**
     * <p>
     * 测试通用 Api Controller 逻辑
     * </p>
     * 测试地址：
     * http://localhost:8080/user/api
     * http://localhost:8080/user/api?test=mybatisplus
     */
    @GetMapping("/api")
    public ApiResult<String> testError(String test) {
        ApiAssert.isNull(ErrorCode.TEST, test);
        return success(test);
    }

    /**
     * http://localhost:8080/user/test
     */
    @GetMapping("/test")
    public IPage<User> test() {
        return userService.selectPage(new Page<User>(0, 12), null);
    }


}
