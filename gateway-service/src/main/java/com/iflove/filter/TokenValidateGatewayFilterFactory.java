package com.iflove.filter;

import cn.hutool.core.collection.CollectionUtil;
import com.iflove.config.Config;
import com.iflove.starter.bases.constant.UserConstant;
import com.iflove.starter.common.constant.RedisKey;
import com.iflove.utils.JWTUtil;
import com.iflove.utils.RedisUtil;
import com.iflove.utils.UserInfoDTO;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote SpringCloud Gateway Token 拦截器
 */

@Component
public class TokenValidateGatewayFilterFactory extends AbstractGatewayFilterFactory<Config> {

    public TokenValidateGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            String requestPath = request.getPath().toString();
            // 如果不在白名单中，说明需要token校验
            if (!isPathInWhitePreList(requestPath, config.getWhitePathPre())) {
                String token = request.getHeaders().getFirst("Authorization");
                // 解析token
                UserInfoDTO userInfoDTO = JWTUtil.parseJwtToken(token);
                // 如果解析失败，返回
                if (Objects.isNull(userInfoDTO)) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }

                String tokenId = userInfoDTO.getTokenId();
                // 如果该 token 被列入黑名单 或者 token 已过期，拒绝访问
                if (RedisUtil.getStr(RedisKey.getKey(RedisKey.JWT_BLACK_LIST, tokenId)) != null
                        || RedisUtil.getStr(RedisKey.getKey(RedisKey.JWT_WHITE_LIST, tokenId)) == null) {
                    ServerHttpResponse response = exchange.getResponse();
                    response.setStatusCode(HttpStatus.UNAUTHORIZED);
                    return response.setComplete();
                }
                // 构建Header信息
                ServerHttpRequest.Builder builder = exchange.getRequest().mutate().headers(httpHeaders -> {
                    httpHeaders.set(UserConstant.USER_ID_KEY, userInfoDTO.getUserId());
                    httpHeaders.set(UserConstant.USER_NAME_KEY, userInfoDTO.getUsername());
                    httpHeaders.set(UserConstant.USER_TOKEN_KEY, userInfoDTO.getTokenId());
                });
                return chain.filter(exchange.mutate().request(builder.build()).build());
            }
            return chain.filter(exchange);
        };
    }

    private boolean isPathInWhitePreList(String requestPath, List<String> whitePathPre) {
        if (CollectionUtil.isEmpty(whitePathPre)) {
            return false;
        }
        return whitePathPre.stream().anyMatch(requestPath::startsWith);
    }
}
