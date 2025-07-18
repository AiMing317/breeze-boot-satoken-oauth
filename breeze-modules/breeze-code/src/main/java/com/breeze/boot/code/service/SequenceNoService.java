/*
 * Copyright (c) 2025, gaoweixuan (breeze-cloud@foxmail.com).
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

package com.breeze.boot.code.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.breeze.boot.code.model.entity.SequenceNo;

/**
 * 自动编码服务
 *
 * @author gaoweixuan
 * @since 2023-03-06
 */
public interface SequenceNoService extends IService<SequenceNo> {

    String generateCode(String customPrefix, String sequenceType);

    String generateCode(String customPrefix, String sequenceType, Integer length);

}

