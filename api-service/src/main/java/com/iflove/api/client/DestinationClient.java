package com.iflove.api.client;

import com.iflove.api.dto.DestinationInfoResp;
import com.iflove.starter.convention.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 目的地模块远程调用
 */
@FeignClient("destination-service")
public interface DestinationClient {
    @GetMapping("api/destination/id/{id}")
    Result<DestinationInfoResp> getById(@PathVariable Long id);
}
