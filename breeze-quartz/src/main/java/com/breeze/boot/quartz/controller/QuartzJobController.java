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

package com.breeze.boot.quartz.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.boot.core.utils.Result;
import com.breeze.boot.log.annotation.BreezeSysLog;
import com.breeze.boot.log.enums.LogType;
import com.breeze.boot.quartz.domain.SysQuartzJob;
import com.breeze.boot.quartz.domain.params.JobOpenParam;
import com.breeze.boot.quartz.domain.query.JobQuery;
import com.breeze.boot.quartz.service.SysQuartzJobService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * Quartz任务控制器
 *
 * @author gaoweixuan
 * @since 2023-03-16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/v1/job")
public class QuartzJobController {

    /**
     * quartz 任务服务
     */
    private final SysQuartzJobService sysQuartzJobService;

    /**
     * 列表页面
     *
     * @param jobQuery 任务查询
     * @return {@link Result}<{@link Page}<{@link SysQuartzJob}>>
     */
    @Operation(summary = "列表")
    @GetMapping
    @SaCheckPermission("sys:job:list")
    public Result<Page<SysQuartzJob>> listPage(JobQuery jobQuery) {
        return Result.ok(this.sysQuartzJobService.listPage(jobQuery));
    }

    /**
     * 详情
     *
     * @param jobId 任务Id
     * @return {@link Result}<{@link SysQuartzJob}>
     */
    @Operation(summary = "详情")
    @GetMapping("/info/{jobId}")
    @SaCheckPermission("sys:job:info")
    public Result<SysQuartzJob> info(@PathVariable("jobId") Long jobId) {
        return Result.ok(this.sysQuartzJobService.getById(jobId));
    }

    /**
     * 保存
     *
     * @param sysQuartzJob quartz任务
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "保存")
    @PostMapping
    @SaCheckPermission("sys:job:create")
    @BreezeSysLog(description = "保存任务", type = LogType.EDIT)
    public Result<Boolean> save(@Valid @RequestBody SysQuartzJob sysQuartzJob) {
        return this.sysQuartzJobService.saveJob(sysQuartzJob);
    }

    /**
     * 修改
     *
     * @param sysQuartzJob quartz任务
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "修改")
    @PutMapping
    @SaCheckPermission("sys:job:modify")
    @BreezeSysLog(description = "修改任务", type = LogType.EDIT)
    public Result<Boolean> modify(@Valid @RequestBody SysQuartzJob sysQuartzJob) {
        return this.sysQuartzJobService.updateJobById(sysQuartzJob);
    }

    /**
     * 开启或关闭
     *
     * @param jobOpenParam 任务的开启关闭参数
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "开启或关闭")
    @PutMapping("/open")
    @SaCheckPermission("sys:job:modify")
    @BreezeSysLog(description = "开启或关闭任务", type = LogType.EDIT)
    public Result<Boolean> open(@Valid @RequestBody JobOpenParam jobOpenParam) {
        return this.sysQuartzJobService.open(jobOpenParam);
    }

    /**
     * 删除
     *
     * @param jobIds 任务ids
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "删除")
    @DeleteMapping
    @SaCheckPermission("sys:job:delete")
    @BreezeSysLog(description = "删除任务", type = LogType.DELETE)
    public Result<Boolean> delete(@NotNull(message = "参数不能为空") @RequestBody Long[] jobIds) {
        return this.sysQuartzJobService.deleteJob(Arrays.asList(jobIds));
    }

    /**
     * 立刻运行
     *
     * @param jobId 任务ID
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "立刻运行")
    @GetMapping("/runJobNow")
    @SaCheckPermission("sys:job:run")
    public Result<Boolean> runJobNow(@NotNull(message = "参数不能为空") @RequestParam Long jobId) {
        return this.sysQuartzJobService.runJobNow(jobId);
    }
}
