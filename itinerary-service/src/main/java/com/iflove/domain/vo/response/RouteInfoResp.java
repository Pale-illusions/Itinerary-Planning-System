package com.iflove.domain.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
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
public class RouteInfoResp {
    @Schema(description = "行程信息id")
    private Long id;

    @Schema(description = "起始地")
    private String startLocation;

    @Schema(description = "目的地")
    private String endLocation;

    @Schema(description = "价格")
    private Integer price;

    @Schema(description = "耗时")
    private Time duratoin;
}
