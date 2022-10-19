/*
 * Copyright (c) 2021-2022, gaoweixuan (breeze-cloud@foxmail.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.breeze.boot.log.config;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

import java.util.function.Consumer;

/**
 * 系统日志保存事件监听器
 *
 * @author breeze
 * @date 2022-10-19
 */
public class SysLogSaveEventListener implements ApplicationListener<ApplicationEvent> {

    private Consumer<ApplicationEvent> consumer;

    @Override
    @Async
    @EventListener(SysLogSaveEvent.class)
    public void onApplicationEvent(ApplicationEvent event) {
        consumer.accept(event);
    }

}
