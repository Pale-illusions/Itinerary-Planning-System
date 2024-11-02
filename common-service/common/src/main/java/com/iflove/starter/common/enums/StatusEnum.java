package com.iflove.starter.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 状态是否正常枚举类
 */
@AllArgsConstructor
@Getter
public enum StatusEnum {
    NORMAL(0, "正常"),
    FORBIDDEN(1, "禁用"),
    ;

    private final Integer status;
    private final String desc;

    private static final Map<Integer, StatusEnum> cache;

    static {
        cache = Arrays.stream(StatusEnum.values()).collect(Collectors.toMap(StatusEnum::getStatus, Function.identity()));
    }

    public static StatusEnum of(Integer type) {
        return cache.get(type);
    }

    public static Integer toStatus(Boolean bool) {
        return bool ? NORMAL.getStatus() : FORBIDDEN.getStatus();
    }
}
