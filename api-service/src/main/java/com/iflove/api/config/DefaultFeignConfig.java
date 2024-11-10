package com.iflove.api.config;

import com.iflove.api.client.fallback.DestinationClientFallback;
import com.iflove.api.client.fallback.UserClientFallback;
import com.iflove.starter.bases.constant.UserConstant;
import com.iflove.starter.user.core.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.Objects;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote OpenFeign配置类
 */
@Configuration
@Slf4j
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLogLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate template) {
                // 获取登录用户
                String userId = UserContext.getUserId();
                String username = UserContext.getUsername();
                String tokenId = UserContext.getTokenId();
                if (Objects.isNull(userId) || Objects.isNull(username) || Objects.isNull(tokenId)) {
                    // 如果为空则直接跳过
                    log.info("用户信息为空，无法传递");
                    return;
                }
                // 如果不为空则放入请求头中，传递给下游微服务
                template.header(UserConstant.USER_ID_KEY, userId);
                template.header(UserConstant.USER_NAME_KEY, username);
                template.header(UserConstant.USER_TOKEN_KEY, tokenId);
            }
        };
    }

    @Bean
    public DestinationClientFallback destinationClientFallback() {
        return new DestinationClientFallback();
    }

    @Bean
    public UserClientFallback userClientFallback() {
        return new UserClientFallback();
    }
}
