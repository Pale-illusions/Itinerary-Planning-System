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
 * @implNote 删除状态枚举
 */
@AllArgsConstructor
@Getter
public enum DelEnum {
    NORMAL(0, "正常"),
    DELETE(1, "删除"),
    ;

    private final Integer status;
    private final String desc;

    private static final Map<Integer, DelEnum> cache;

    static {
        cache = Arrays.stream(DelEnum.values()).collect(Collectors.toMap(DelEnum::getStatus, Function.identity()));
    }

    public static DelEnum of(Integer type) {
        return cache.get(type);
    }

    public static Integer toStatus(Boolean bool) {
        return bool ? NORMAL.getStatus() : DELETE.getStatus();
    }
}
