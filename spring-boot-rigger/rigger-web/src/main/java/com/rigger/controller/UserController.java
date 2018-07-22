package com.rigger.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.ApiAssert;
import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.ApiResult;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rigger.model.User;
import com.rigger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private UserService userService;

    @GetMapping("/api")
    public ApiResult<String> api(String test) {
//        ApiAssert.isNull(ErrorCode.TEST, test);
        return success(test);
    }

    @GetMapping("/selectPage")
    public IPage<User> selectPage(){
        return userService.selectPage(new Page<User>(),null);
    }

    @GetMapping("/page")
    public IPage<User> page(Page<User> page){
        return userService.selectPage(page,null);
    }

    @GetMapping("/selectById")
    public  ApiResult<User> selectById(@RequestParam(value="id") Integer id) {
        User user = userService.selectById(id);
        return super.success(user);
    }
}
