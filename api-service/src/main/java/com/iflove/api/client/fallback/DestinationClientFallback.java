package com.iflove.api.client.fallback;

import com.iflove.api.client.DestinationClient;
import com.iflove.api.dto.DestinationInfoResp;
import com.iflove.api.dto.Results;
import com.iflove.starter.convention.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote DestinationClient熔断降级
 */
@Slf4j
public class DestinationClientFallback implements FallbackFactory<DestinationClient> {
    @Override
    public DestinationClient create(Throwable cause) {
        return new DestinationClient() {
            @Override
            public Result<DestinationInfoResp> getById(Long id) {
                log.error("远程调用Destination#getById方法出现异常，参数：{}", id, cause);
                return Results.success(DestinationInfoResp.builder()
                        .id(-1L)
                        .name("")
                        .build()
                );
            }
        };
    }
}
