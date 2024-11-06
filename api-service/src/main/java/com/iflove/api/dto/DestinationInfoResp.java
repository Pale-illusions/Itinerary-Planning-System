package com.iflove.api.dto;

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
public class DestinationInfoResp {
    private Long id;
    private String name;
}
