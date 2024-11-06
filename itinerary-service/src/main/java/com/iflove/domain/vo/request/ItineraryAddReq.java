package com.iflove.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 行程添加请求体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "行程添加请求")
public class ItineraryAddReq {

    @Schema(description = "行程信息Id")
    @NotNull
    private Long routeId;
}
