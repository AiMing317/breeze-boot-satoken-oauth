/*
 * Copyright (c) 2023, gaoweixuan (breeze-cloud@foxmail.com).
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

package com.breeze.boot.modules.bpm.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.breeze.boot.modules.bpm.model.entity.BpmCategory;
import com.breeze.boot.modules.bpm.model.form.BpmCategoryForm;
import com.breeze.boot.modules.bpm.model.query.BpmCategoryQuery;
import com.breeze.boot.modules.bpm.model.vo.BpmCategoryVO;

import javax.validation.Valid;

/**
 * 流程分类服务
 *
 * @author gaoweixuan
 * @since 2023-03-06
 */
public interface IBpmCategoryService extends IService<BpmCategory> {

    /**
     * 列表页面
     *
     * @param processCategory 流程分类
     * @return {@link Page}<{@link BpmCategoryVO}>
     */
    Page<BpmCategoryVO> listPage(BpmCategoryQuery processCategory);

    /**
     * 按id获取信息
     *
     * @param categoryId 类别id
     * @return {@link BpmCategory }
     */
    BpmCategoryVO getInfoById(Long categoryId);

    /**
     * 保存流类别
     *
     * @param bpmCategoryForm 流程分类表单
     * @return {@link Boolean }
     */
    Boolean saveFlowCategory(BpmCategoryForm bpmCategoryForm);

    /**
     * 修改流类别
     *
     * @param id ID
     * @param bpmCategoryForm 流程分类表单
     * @return {@link Boolean }
     */
    Boolean modifyFlowCategory(Long id, @Valid BpmCategoryForm bpmCategoryForm);

}
