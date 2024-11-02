package com.iflove.starter.log.config;

import com.iflove.starter.log.annotation.ILog;
import com.iflove.starter.log.core.ILogPrintAspect;
import org.springframework.context.annotation.Bean;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 日志自动装配
 */

public class LogAutoConfiguration {
    /**
     * {@link ILog} 日志打印 AOP 切面
     */
    @Bean
    public ILogPrintAspect iLogPrintAspect() {
        return new ILogPrintAspect();
    }
}
