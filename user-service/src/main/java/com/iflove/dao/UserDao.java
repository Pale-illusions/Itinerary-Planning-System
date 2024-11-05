package com.iflove.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.iflove.domain.entity.User;
import com.iflove.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author cangjingyue
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2024-11-05 08:36:33
*/
@Service
public class UserDao extends ServiceImpl<UserMapper, User> {

}




