package com.iflove.starter.log.core;

import lombok.Data;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote ILog 日志打印实体
 */

@Data
public class ILogPrintDTO {
    /**
     * 开始时间
     */
    private String beginTime;

    /**
     * 请求入参
     */
    private Object[] inputParams;

    /**
     * 返回参数
     */
    private Object outputParams;
}
