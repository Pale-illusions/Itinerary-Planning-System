package com.iflove.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 用户信息返回体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfoResp {
    private Long id;

    private String name;
}
