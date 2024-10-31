package com.iflove.starter.bases.event;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 应用初始化后置处理器，防止Spring事件被多次执行
 */

public class ApplicationContentPostProcessor implements ApplicationListener<ApplicationReadyEvent> {

    private final ApplicationContext applicationContext;

    /**
     * 执行标识，确保Spring事件 {@link ApplicationReadyEvent} 有且执行一次
     */
    private final AtomicBoolean executeOnlyOnce = new AtomicBoolean(false);

    public ApplicationContentPostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!executeOnlyOnce.compareAndSet(false, true)) {
            return;
        }
        applicationContext.publishEvent(new ApplicationInitializingEvent(this));
    }
}
