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

package com.breeze.boot.system.controller;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.core.utils.Result;
import com.breeze.log.annotation.BreezeSysLog;
import com.breeze.log.config.LogType;
import com.breeze.boot.system.domain.SysMenu;
import com.breeze.boot.system.domain.SysPlatform;
import com.breeze.boot.system.dto.PlatformSearchDTO;
import com.breeze.boot.system.service.SysMenuService;
import com.breeze.boot.system.service.SysPlatformService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * 系统平台控制器
 *
 * @author gaoweixuan
 * @date 2021-12-06
 */
@RestController
@SecurityRequirement(name = "Bearer")
@RequestMapping("/sys/platform")
@Tag(name = "系统平台管理模块", description = "SysPlatformController")
public class SysPlatformController {

    /**
     * 系统平台服务
     */
    @Autowired
    private SysPlatformService sysPlatformService;

    /**
     * 系统菜单服务
     */
    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 列表
     *
     * @param platformSearchDTO 平台搜索DTO
     * @return {@link Result}<{@link Page}<{@link SysPlatform}>>
     */
    @Operation(summary = "列表")
    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('sys:platform:list')")
    public Result<Page<SysPlatform>> list(@RequestBody PlatformSearchDTO platformSearchDTO) {
        return Result.ok(this.sysPlatformService.listPage(platformSearchDTO));
    }

    /**
     * 校验平台编码是否重复
     *
     * @param platformCode 平台编码
     * @param platformId   平台ID
     * @return {@link Result}<{@link SysPlatform}>
     */
    @Operation(summary = "校验平台编码是否重复")
    @GetMapping("/checkPlatformCode")
    @PreAuthorize("hasAnyAuthority('sys:platform:list')")
    public Result<Boolean> checkPlatformCode(@RequestParam("platformCode") String platformCode,
                                             @RequestParam(value = "platformId", required = false) Long platformId) {
        return Result.ok(Objects.isNull(this.sysPlatformService.getOne(Wrappers.<SysPlatform>lambdaQuery()
                .ne(Objects.nonNull(platformId), SysPlatform::getId, platformId)
                .eq(SysPlatform::getPlatformCode, platformCode))));
    }

    /**
     * 创建
     *
     * @param platform 平台实体入参
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "保存")
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('sys:platform:create')")
    @BreezeSysLog(description = "平台信息保存", type = LogType.SAVE)
    public Result<Boolean> save(@Validated @RequestBody SysPlatform platform) {
        return Result.ok(this.sysPlatformService.save(platform));
    }

    /**
     * 修改
     *
     * @param sysPlatform 平台实体
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "修改")
    @PutMapping("/modify")
    @PreAuthorize("hasAnyAuthority('sys:platform:modify')")
    @BreezeSysLog(description = "平台信息修改", type = LogType.EDIT)
    public Result<Boolean> modify(@Validated @RequestBody SysPlatform sysPlatform) {
        return Result.ok(this.sysPlatformService.updateById(sysPlatform));
    }

    /**
     * 删除
     *
     * @param ids id
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "删除")
    @DeleteMapping("/delete")
    @PreAuthorize("hasAnyAuthority('sys:platform:delete')")
    @BreezeSysLog(description = "平台信息删除", type = LogType.DELETE)
    public Result<Boolean> delete(@NotNull(message = "参数不能为空") @RequestBody Long[] ids) {
        List<SysMenu> menuEntityList = this.sysMenuService.list(Wrappers.<SysMenu>lambdaQuery().in(SysMenu::getPlatformId, ids));
        if (CollectionUtil.isNotEmpty(menuEntityList)) {
            return Result.warning(Boolean.FALSE, "该平台有菜单配置");
        }
        return Result.ok(this.sysPlatformService.removeByIds(Arrays.asList(ids)));
    }

}
