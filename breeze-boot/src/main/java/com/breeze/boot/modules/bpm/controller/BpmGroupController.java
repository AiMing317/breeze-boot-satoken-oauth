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

package com.breeze.boot.modules.bpm.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.boot.core.utils.Result;
import com.breeze.boot.modules.bpm.model.query.BpmGroupQuery;
import com.breeze.boot.modules.bpm.model.vo.BpmGroupVO;
import com.breeze.boot.modules.bpm.model.vo.BpmUserVO;
import com.breeze.boot.modules.bpm.service.IGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 流程用户组管理模块
 *
 * @author gaoweixuan
 * @since 2023-03-01
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/bpm/v1/group")
@Tag(name = "流程用户组管理模块", description = "BpmGroupController")
public class BpmGroupController {

    private final IGroupService groupService;

    /**
     * 列表
     *
     * @param groupQuery 用户组查询
     * @return {@link Result }<{@link Page }<{@link BpmUserVO }>>
     */
    @Operation(summary = "列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('bpm:group:list')")
    public Result<Page<BpmGroupVO>> list(BpmGroupQuery groupQuery) {
        return Result.ok(this.groupService.listPage(groupQuery));
    }

}
