package com.iflove.starter.database.handler;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.iflove.starter.distributedid.utils.SnowflakeIdUtil;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 自定义雪花算法生成器
 */

public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Number nextId(Object entity) {
        return SnowflakeIdUtil.nextId();
    }
}
