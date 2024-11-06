package com.iflove.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 行程信息添加请求
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "行程信息添加请求")
public class RouteAddReq {
    @Schema(description = "出发地")
    @NotNull(message = "出发地不能为空")
    private Long start_location;

    @NotNull(message = "目的地不能为空")
    @Schema(description = "目的地")
    private Long end_location;

    @Schema(description = "价格 (格式：000000 前4个0代表整数部分，后2个0代表小数部分)")
    @NotNull(message = "价格不能为空")
    @Positive(message = "价格必须是正数")
    @Max(value = 999999, message = "价格不能超过 999999")
    private Integer price; // 假设价格单位为分，最大值999999

    @Schema(description = "耗时")
    @NotNull(message = "耗时不能为空")
    @Pattern(regexp = "(0[0-9]|1[0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])", message = "耗时格式必须为 HH:mm:ss")
    private String duration; // 耗时格式为 HH:mm:ss

    @Schema(hidden = true)
    public LocalTime getDurationAsLocalTime() {
        return LocalTime.parse(duration); // 将 duration 字符串转换为 LocalTime
    }
}
