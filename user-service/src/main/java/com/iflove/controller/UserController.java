package com.iflove.controller;

import com.iflove.domain.vo.response.UserInfoResp;
import com.iflove.service.UserService;
import com.iflove.starter.convention.result.Result;
import com.iflove.starter.frequencycontrol.annotation.FrequencyControl;
import com.iflove.starter.frequencycontrol.domain.enums.FrequencyControlStrategyEnum;
import com.iflove.starter.frequencycontrol.domain.enums.FrequencyControlTargetEnum;
import com.iflove.starter.log.annotation.ILog;
import com.iflove.starter.user.core.UserContext;
import com.iflove.starter.web.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote
 */
@RestController
@RequestMapping("api/user")
@Validated
@Tag(name = "用户模块")
@RequiredArgsConstructor
@ILog
public class UserController {
    private final UserService userService;

    /**
     * 登出
     * @return {@link Result}
     */
    @GetMapping("logout")
    @Operation(summary = "登出", description = "用户登出")
    public Result<Void> logout() {
        userService.logout(UserContext.getTokenId());
        return Results.success();
    }

    @FrequencyControl(time = 5, count = 3, target = FrequencyControlTargetEnum.UID)
    @FrequencyControl(time = 30, count = 5, target = FrequencyControlTargetEnum.UID)
    @FrequencyControl(time = 60, count = 10, target = FrequencyControlTargetEnum.UID)
    @GetMapping("userInfo/me")
    @Operation(summary = "获取用户信息(当前用户)", description = "获取用户信息(当前用户)")
    public Result<UserInfoResp> getUserInfo() {
        return Results.success(userService.getUserInfo(Long.parseLong((UserContext.getUserId()))));
    }

    @FrequencyControl(strategy = FrequencyControlStrategyEnum.SLIDING_WINDOW, target = FrequencyControlTargetEnum.EL, spEl = "#id")
    @FrequencyControl(strategy = FrequencyControlStrategyEnum.TOKEN_BUCKET, target = FrequencyControlTargetEnum.EL, spEl = "#id")
    @GetMapping("userInfo/{id}")
    @Operation(summary = "获取用户信息", description = "获取用户信息")
    public Result<UserInfoResp> getUserInfo(@PathVariable Long id) {
        return Results.success(userService.getUserInfo(id));
    }
}
