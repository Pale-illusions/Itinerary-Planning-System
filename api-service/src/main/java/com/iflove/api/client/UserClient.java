package com.iflove.api.client;

import com.iflove.api.client.fallback.UserClientFallback;
import com.iflove.api.dto.UserInfoResp;
import com.iflove.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 用户模块远程调用
 */
@FeignClient(value = "user-service", fallback = UserClientFallback.class)
public interface UserClient {
    @GetMapping("api/user/userInfo/{id}")
    Result<UserInfoResp> getUserInfo(@PathVariable Long id);
}
