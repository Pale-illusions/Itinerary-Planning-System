package com.iflove.service.adapter;

import com.iflove.domain.entity.User;
import com.iflove.domain.vo.response.UserLoginResp;
import com.iflove.starter.user.core.UserInfoDTO;

import java.util.Date;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public class UserAdapter {

    /**
     * 创建用户登录返回体
     * @return {@link UserLoginResp}
     */
    public static UserLoginResp builduserLoginResp(User user, String token, Date expireTime) {
        return UserLoginResp.builder()
                .id(user.getId())
                .name(user.getName())
                .token(token)
                .expireTime(expireTime)
                .build();
    }
}
