package com.iflove.api.client.fallback;

import com.iflove.api.client.UserClient;
import com.iflove.api.dto.Results;
import com.iflove.api.dto.UserInfoResp;
import com.iflove.starter.convention.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote UserClient熔断降级
 */

@Slf4j
public class UserClientFallback implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public Result<UserInfoResp> getUserInfo(Long id) {
                log.error("远程调用UserClient#getUserInfo方法出现异常，参数：{}", id, cause);
                return Results.success(UserInfoResp.builder()
                        .id(-1L)
                        .name("")
                        .build()
                );
            }
        };
    }
}
