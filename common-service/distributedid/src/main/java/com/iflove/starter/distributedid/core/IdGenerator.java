package com.iflove.starter.distributedid.core;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote ID生成器
 */

public interface IdGenerator {
    /**
     * 下一个 ID
     */
    default long nextId() {
        return 0L;
    }

    /**
     * 下一个 ID 字符串
     */
    default String nextIdStr() {
        return "";
    }
}
