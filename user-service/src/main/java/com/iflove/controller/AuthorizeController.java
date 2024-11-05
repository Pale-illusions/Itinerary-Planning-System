package com.iflove.controller;

import com.iflove.domain.vo.request.UserLoginReq;
import com.iflove.domain.vo.response.UserLoginResp;
import com.iflove.service.UserService;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.web.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@RestController
@RequestMapping("api/user/auth")
@Validated
@Tag(name = "用户权限模块")
@RequiredArgsConstructor
public class AuthorizeController {
    private final UserService userService;

    /**
     * 登录
     * @param req 用户登录请求体
     * @return {@link Result}<{@link UserLoginResp}
     */
    @PostMapping("login")
    @Operation(summary = "登录", description = "用户登录")
    public Result<UserLoginResp> login(@RequestBody @Valid UserLoginReq req) {
        return Results.success(userService.login(req));
    }


}
