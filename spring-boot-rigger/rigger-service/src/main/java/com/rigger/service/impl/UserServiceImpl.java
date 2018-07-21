package com.rigger.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rigger.mapper.UserMapper;
import com.rigger.model.User;
import com.rigger.service.UserService;
import org.springframework.stereotype.Service;

/**
 *
 * User 表数据服务层接口实现类
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


}