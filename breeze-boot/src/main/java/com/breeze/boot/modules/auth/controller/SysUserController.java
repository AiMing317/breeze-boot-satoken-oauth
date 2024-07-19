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

package com.breeze.boot.modules.auth.controller;

import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.boot.core.base.UserInfoDTO;
import com.breeze.boot.core.utils.Result;
import com.breeze.boot.log.annotation.BreezeSysLog;
import com.breeze.boot.log.enums.LogType;
import com.breeze.boot.modules.auth.model.entity.SysUser;
import com.breeze.boot.modules.auth.model.form.*;
import com.breeze.boot.modules.auth.model.query.UserQuery;
import com.breeze.boot.modules.auth.model.vo.UserVO;
import com.breeze.boot.modules.auth.service.SysUserService;
import com.breeze.boot.security.utils.SecurityUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

/**
 * 系统用户控制器
 *
 * @author gaoweixuan
 * @since 2021-12-06 22:03:39
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer")
@RequestMapping("/auth/v1/user")
@Tag(name = "系统用户管理模块", description = "SysUserController")
public class SysUserController {

    /**
     * 系统用户服务
     */
    private final SysUserService sysUserService;

    /**
     * 列表
     *
     * @param userQuery 用户查询
     * @return {@link Result}<{@link Page}<{@link UserVO}>>
     */
    @Operation(summary = "列表")
    @GetMapping
    @PreAuthorize("hasAnyAuthority('auth:user:list')")
    public Result<Page<UserVO>> list(UserQuery userQuery) {
        return Result.ok(this.sysUserService.listPage(userQuery));
    }

    /**
     * 详情
     *
     * @param userId 用户id
     * @return {@link Result}<{@link SysUser}>
     */
    @Operation(summary = "详情")
    @GetMapping("/info/{userId}")
    @PreAuthorize("hasAnyAuthority('auth:user:info')")
    public Result<UserVO> info(@PathVariable("userId") Long userId) {
        return Result.ok(this.sysUserService.getInfoById(userId));
    }

    /**
     * 创建
     *
     * @param userForm 用户表单
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "保存")
    @PostMapping
    @PreAuthorize("hasAnyAuthority('auth:user:create')")
    @BreezeSysLog(description = "用户信息保存", type = LogType.SAVE)
    public Result<Boolean> save(@Valid @RequestBody UserForm userForm) {
        return sysUserService.saveUser(userForm);
    }

    /**
     * 修改
     *
     * @param id       id
     * @param userForm 用户表单
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "修改")
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('auth:user:modify')")
    @BreezeSysLog(description = "用户信息修改", type = LogType.EDIT)
    public Result<Boolean> modify(@Parameter(description = "用户ID") @NotNull(message = "用户ID不能为空") Long id,
                                  @Valid @RequestBody UserForm userForm) {
        return Result.ok(sysUserService.modifyUser(id, userForm));
    }

    /**
     * 修改
     *
     * @param userAvatarForm 系统用户
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "修改头像")
    @PutMapping("/modifyAvatar")
    @PreAuthorize("hasAnyAuthority('auth:user:modify')")
    @BreezeSysLog(description = "用户头像信息修改", type = LogType.EDIT)
    public Result<Boolean> modifyAvatar(@Valid @RequestBody UserAvatarForm userAvatarForm) {
        return Result.ok(sysUserService.update(Wrappers.<SysUser>lambdaUpdate().set(SysUser::getAvatar, userAvatarForm.getAvatar()).set(SysUser::getAvatarFileId, userAvatarForm.getAvatarFileId()).eq(SysUser::getId, userAvatarForm.getId())));
    }

    /**
     * 校验用户名是否重复
     *
     * @param username 平台编码
     * @param userId   用户ID
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "校验用户名是否重复")
    @GetMapping("/checkUsername")
    @PreAuthorize("hasAnyAuthority('auth:user:list')")
    public Result<Boolean> checkUsername(@Parameter(description = "用户名") @NotBlank(message = "用户名不能为空") @RequestParam("username") String username,
                                         @Parameter(description = "用户ID") @RequestParam(value = "userId", required = false) Long userId) {
        return Result.ok(Objects.isNull(this.sysUserService.getOne(Wrappers.<SysUser>lambdaQuery().ne(Objects.nonNull(userId), SysUser::getId, userId).eq(SysUser::getUsername, username))));
    }

    /**
     * 查询用户通过部门id
     *
     * @param deptIds 部门id
     * @return {@link Result}<{@link List}<{@link SysUser}>>
     */
    @Operation(summary = "查询用户通过部门id")
    @PostMapping("/listUserByDeptId")
    public Result<List<UserVO>> listUserByDeptId(@Parameter(description = "部门IDS") @NotEmpty(message = "部门ID不能为空")
                                                 @RequestBody List<Long> deptIds) {
        return Result.ok(this.sysUserService.listUserByDeptId(deptIds));
    }

    /**
     * 删除
     *
     * @param ids 用户ids
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "删除")
    @DeleteMapping
    @PreAuthorize("hasAnyAuthority('auth:user:delete')")
    @BreezeSysLog(description = "用户信息删除", type = LogType.DELETE)
    public Result<Boolean> delete(@Parameter(description = "用户ID") @NotNull(message = "参数不能为空") @RequestBody List<Long> ids) {
        return this.sysUserService.removeUser(ids);
    }

    /**
     * 导出
     *
     * @param response 响应
     */
    @Operation(summary = "导出")
    @GetMapping("/export")
    public void export(HttpServletResponse response) {
        this.sysUserService.export(response);
    }

    /**
     * 重置密码
     *
     * @param userResetForm 用户重置密码参数
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "重置密码")
    @PutMapping("/reset")
    @PreAuthorize("hasAnyAuthority('auth:user:reset')")
    @BreezeSysLog(description = "用户重置密码", type = LogType.EDIT)
    public Result<Boolean> reset(@Valid @RequestBody UserResetForm userResetForm) {
        return Result.ok(sysUserService.reset(userResetForm));
    }

    /**
     * 开启关闭锁定
     *
     * @param userOpenForm 用户开关表单
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "用户锁定开关")
    @PutMapping("/open")
    @PreAuthorize("hasAnyAuthority('auth:user:modify')")
    @BreezeSysLog(description = "用户锁定", type = LogType.EDIT)
    public Result<Boolean> open(@Valid @RequestBody UserOpenForm userOpenForm) {
        return Result.ok(sysUserService.open(userOpenForm));
    }

    /**
     * 用户分配角色
     * <p>
     * /listUserRoles 不使用权限标识，直接使用这个
     *
     * @param userRolesForm 用户角色参数
     * @return {@link Result}<{@link Boolean}>
     */
    @Operation(summary = "用户分配角色")
    @PutMapping("/setRole")
    @PreAuthorize("hasAnyAuthority('auth:user:set:role', 'ROLE_ADMIN')")
    @BreezeSysLog(description = "用户分配角色", type = LogType.EDIT)
    public Result<Boolean> setRole(@Valid @RequestBody UserRolesForm userRolesForm) {
        return sysUserService.setRole(userRolesForm);
    }

    /**
     * 查询用户信息
     *
     * @return {@link String }
     */
    @Operation(summary = "查询用户信息")
    @PreAuthorize("hasAnyAuthority('auth:user:info')")
    @GetMapping("/userInfo")
    public Result<UserInfoDTO> userInfo() {
        log.info("{}", JSONUtil.parse(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
        return Result.ok(SecurityUtils.getCurrentUser());
    }
}
