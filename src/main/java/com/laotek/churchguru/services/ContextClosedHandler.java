package com.laotek.churchguru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

//commented out to enable use of Spring-Boot
//@Component
public class ContextClosedHandler implements ApplicationListener<ContextClosedEvent> {

    @Autowired
    ThreadPoolTaskExecutor taskExecutor;

    @Autowired
    ThreadPoolTaskScheduler taskScheduler;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
	taskScheduler.shutdown();
	taskExecutor.shutdown();
    }
}
