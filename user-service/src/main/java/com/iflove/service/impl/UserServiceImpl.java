package com.iflove.service.impl;

import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.iflove.dao.UserDao;
import com.iflove.domain.entity.User;
import com.iflove.domain.vo.request.UserLoginReq;
import com.iflove.domain.vo.response.UserLoginResp;
import com.iflove.service.UserService;
import com.iflove.service.adapter.UserAdapter;
import com.iflove.starter.common.constant.RedisKey;
import com.iflove.starter.common.utils.EncryptionUtil;
import com.iflove.starter.convention.exception.ClientException;
import com.iflove.starter.convention.exception.ServiceException;
import com.iflove.starter.database.utils.RedisUtil;
import com.iflove.starter.user.core.UserInfoDTO;
import com.iflove.starter.user.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.ServerException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    /**
     * 登录
     * @param req 用户登录请求体
     * @return {@link UserLoginResp}
     */
    @Override
    public UserLoginResp login(UserLoginReq req) {
        User user = userDao.getUserByName(req.getUsername());
        // 用户不存在
        if (Objects.isNull(user)) {
            throw new ClientException("用户名不存在");
        }
        // 密码不匹配
        try {
            if (!EncryptionUtil.matchesAES(req.getPassword(), user.getPassword(), EncryptionUtil.PASSWORD)) {
                throw new ServiceException("密码不正确");
            }
        } catch (Exception e) {
            throw new ServiceException("密码解析错误");
        }
        // token uuid
        String tokenId = IdUtil.simpleUUID();
        // token过期时间
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.HOUR, RedisKey.TOKEN_EXPIRE_TIME);
        Date expireTime = calendar.getTime();
        // 获取token
        UserInfoDTO userInfoDTO = UserInfoDTO.builder()
                .userId(String.valueOf(user.getId()))
                .username(user.getName())
                .tokenId(tokenId)
                .build();
        String token = JWTUtil.generateAccessToken(userInfoDTO);
        // 将token存入redis
        RedisUtil.set(RedisKey.getKey(RedisKey.JWT_WHITE_LIST, tokenId), "", RedisKey.TOKEN_EXPIRE_TIME, TimeUnit.HOURS);
        // 返回结果
        return UserAdapter.builduserLoginResp(user, token, expireTime);
    }
}
