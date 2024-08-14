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

package com.breeze.boot.modules.bpm.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.breeze.boot.modules.bpm.mapper.BpmCategoryMapper;
import com.breeze.boot.modules.bpm.model.entity.BpmCategory;
import com.breeze.boot.modules.bpm.model.form.BpmCategoryForm;
import com.breeze.boot.modules.bpm.model.mappers.BpmCategoryMapStruct;
import com.breeze.boot.modules.bpm.model.query.BpmCategoryQuery;
import com.breeze.boot.modules.bpm.model.vo.BpmCategoryVO;
import com.breeze.boot.modules.bpm.service.IBpmCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 流程分类服务impl
 *
 * @author gaoweixuan
 * @since 2023-03-06
 */
@Service
@DS("flowable")
@RequiredArgsConstructor
public class BpmCategoryServiceImpl extends ServiceImpl<BpmCategoryMapper, BpmCategory> implements IBpmCategoryService {

    private final BpmCategoryMapStruct bpmCategoryMapStruct;

    /**
     * 列表页面
     *
     * @param processCategory 流程类别
     * @return {@link IPage}<{@link BpmCategory}>
     */
    @Override
    public Page<BpmCategoryVO> listPage(BpmCategoryQuery processCategory) {
        Page<BpmCategory> flowCategoryPage = this.baseMapper.listPage(new Page<>(processCategory.getCurrent(), processCategory.getSize()), processCategory);
        return bpmCategoryMapStruct.entityPage2VOPage(flowCategoryPage);
    }

    /**
     * 按id获取信息
     *
     * @param categoryId 流程分类ID
     * @return {@link BpmCategoryVO }
     */
    @Override
    public BpmCategoryVO getInfoById(Long categoryId) {
        return bpmCategoryMapStruct.entity2VO(this.getById(categoryId));
    }

    /**
     * 保存流类别
     *
     * @param bpmCategoryForm 流程分类表单
     * @return {@link Boolean }
     */
    @Override
    public Boolean saveFlowCategory(BpmCategoryForm bpmCategoryForm) {
        BpmCategory bpmCategory = this.bpmCategoryMapStruct.form2Entity(bpmCategoryForm);
        return this.save(bpmCategory);
    }

    /**
     * 修改流类别
     *
     * @param id              ID
     * @param bpmCategoryForm 流程分类表单
     * @return {@link Boolean }
     */
    @Override
    public Boolean modifyFlowCategory(Long id, BpmCategoryForm bpmCategoryForm) {
        BpmCategory bpmCategory = this.bpmCategoryMapStruct.form2Entity(bpmCategoryForm);
        bpmCategory.setId(id);
        return this.updateById(bpmCategory);
    }
}
