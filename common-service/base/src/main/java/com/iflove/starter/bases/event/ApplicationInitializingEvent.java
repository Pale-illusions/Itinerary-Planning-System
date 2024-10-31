package com.iflove.starter.bases.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author 苍镜月
 * @version 1.0
 * @implNote 应用初始化事件
 */

public class ApplicationInitializingEvent extends ApplicationEvent {

    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public ApplicationInitializingEvent(Object source) {
        super(source);
    }
}