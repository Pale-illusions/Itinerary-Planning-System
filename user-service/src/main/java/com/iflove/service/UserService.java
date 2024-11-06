package com.iflove.service;

import com.iflove.domain.vo.request.UserLoginReq;
import com.iflove.domain.vo.request.UserRegisterReq;
import com.iflove.domain.vo.response.UserInfoResp;
import com.iflove.domain.vo.response.UserLoginResp;
import com.iflove.starter.convention.result.Result;
import jakarta.validation.Valid;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */

public interface UserService {
    /**
     * 登录
     * @param req 用户登录请求体
     * @return {@link UserLoginResp}
     */
    UserLoginResp login(@Valid UserLoginReq req);

    /**
     * 注册
     * @param req 用户注册请求体
     */
    void register(@Valid UserRegisterReq req);

    /**
     * 登出
     * @param tokenId tokenId
     */
    void logout(String tokenId);

    UserInfoResp getUserInfo(Long userId);
}
