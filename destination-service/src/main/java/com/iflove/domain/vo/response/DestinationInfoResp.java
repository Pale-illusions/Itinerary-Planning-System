package com.iflove.domain.vo.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 目的地信息返回体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "目的地信息返回体")
public class DestinationInfoResp {
    @Schema(description = "目的地id")
    private Long id;
    @Schema(description = "目的地名")
    private String name;
}
