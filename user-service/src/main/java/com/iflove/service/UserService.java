package com.iflove.service;

import com.iflove.domain.vo.request.UserLoginReq;
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
}
