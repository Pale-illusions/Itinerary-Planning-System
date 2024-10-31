package com.iflove.starter.bases.config;

import com.iflove.starter.bases.ApplicationContextHolder;
import com.iflove.starter.bases.event.ApplicationContentPostProcessor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 基础自动装配
 */

public class ApplicationBaseAutoConfiguration {
    @Bean
    @ConditionalOnMissingBean
    public ApplicationContextHolder congoApplicationContextHolder() {
        return new ApplicationContextHolder();
    }

    @Bean
    @ConditionalOnMissingBean
    public ApplicationContentPostProcessor congoApplicationContentPostProcessor(ApplicationContext applicationContext) {
        return new ApplicationContentPostProcessor(applicationContext);
    }
}
