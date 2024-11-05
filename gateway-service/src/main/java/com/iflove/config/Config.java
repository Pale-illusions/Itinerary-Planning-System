package com.iflove.config;

import lombok.Data;

import java.util.List;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 过滤器配置
 */

@Data
public class Config {
    /**
     * 白名单前置路径
     */
    private List<String> whitePathPre;
}
