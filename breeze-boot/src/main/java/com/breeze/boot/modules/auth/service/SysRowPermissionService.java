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

package com.breeze.boot.modules.auth.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.breeze.boot.core.utils.Result;
import com.breeze.boot.modules.auth.model.entity.SysRowPermission;
import com.breeze.boot.modules.auth.model.form.RowPermissionForm;
import com.breeze.boot.modules.auth.model.query.RowPermissionQuery;
import com.breeze.boot.modules.auth.model.vo.RowPermissionVO;

import java.util.List;

/**
 * 系统行级数据权限服务
 *
 * @author gaoweixuan
 * @since 2022-10-30
 */
public interface SysRowPermissionService extends IService<SysRowPermission> {

    void init();

    /**
     * 列表页面
     *
     * @param rowPermissionQuery 权限查询
     * @return {@link Page}<{@link RowPermissionVO}>
     */
    Page<RowPermissionVO> listPage(RowPermissionQuery rowPermissionQuery);

    /**
     * 按id获取信息
     *
     * @param permissionId 权限id
     * @return {@link RowPermissionVO }
     */
    RowPermissionVO getInfoById(Long permissionId);

    /**
     * 保存数据权限
     *
     * @param rowPermissionForm 行权限表单
     * @return {@link Result}<{@link Boolean}>
     */
    Result<Boolean> saveRowPermission(RowPermissionForm rowPermissionForm);

    /**
     * 删除数据权限通过IDS
     *
     * @param ids ids
     * @return {@link Result}<{@link Boolean}>
     */
    Result<Boolean> removeRowPermissionByIds(List<Long> ids);

    /**
     * 修改数据权限
     *
     * @param id                ID
     * @param rowPermissionForm 数据权限表单
     * @return {@link Result}<{@link Boolean}>
     */
    Result<Boolean> modifyRowPermission(Long id, RowPermissionForm rowPermissionForm);

}
