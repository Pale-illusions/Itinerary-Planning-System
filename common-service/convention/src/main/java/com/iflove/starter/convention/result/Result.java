package com.iflove.starter.convention.result;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 全局响应体
 */

@Data
@Accessors(chain = true)
public class Result<T> implements Serializable {

    @Serial
    private static final long serialVersionUID = 5679018624309023727L;

    /**
     * 正确返回码
     */
    public static final String SUCCESS_CODE = "0";

    /**
     * 返回码
     */
    private String code;

    /**
     * 返回消息
     */
    private String message;

    /**
     * 响应数据
     */
    private T data;

    public boolean isSuccess() {
        return SUCCESS_CODE.equals(code);
    }
}
