package com.iflove.domain.vo.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 目的地添加请求体
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "目的地添加请求体")
public class DestinationAddReq {
    @NotNull
    @Schema(description = "目的地名")
    private String name;
}
