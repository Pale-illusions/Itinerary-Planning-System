package com.iflove.domain.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 行程信息返回体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "行程信息返回体")
public class ItineraryInfoResp {

    @Schema(description = "用户名")
    private String userName;

    @Schema(description = "行程信息")
    private RouteInfoResp routeInfoResp;
}
