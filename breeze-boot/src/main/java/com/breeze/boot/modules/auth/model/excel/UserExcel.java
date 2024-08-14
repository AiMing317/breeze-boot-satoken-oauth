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

package com.breeze.boot.modules.auth.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.*;
import com.alibaba.excel.enums.poi.BorderStyleEnum;
import com.alibaba.excel.enums.poi.HorizontalAlignmentEnum;
import com.alibaba.excel.enums.poi.VerticalAlignmentEnum;
import com.breeze.boot.core.annotation.SensitiveInfo;
import com.breeze.boot.core.enums.SensitiveStrategy;
import com.breeze.boot.security.annotation.SecuredField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * 系统用户Excel
 *
 * @author gaoweixuan
 * @since 2021-12-06 22:03:39
 */
@Data
@Builder
@HeadRowHeight(40)
@ContentRowHeight(35)
@ColumnWidth(20)
@HeadStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,
        verticalAlignment = VerticalAlignmentEnum.CENTER,
        borderBottom = BorderStyleEnum.THIN, borderLeft = BorderStyleEnum.THIN,
        borderTop = BorderStyleEnum.THIN, borderRight = BorderStyleEnum.THIN)
@ContentStyle(horizontalAlignment = HorizontalAlignmentEnum.CENTER,
        verticalAlignment = VerticalAlignmentEnum.CENTER,
        borderBottom = BorderStyleEnum.THIN, borderLeft = BorderStyleEnum.THIN,
        borderTop = BorderStyleEnum.THIN, borderRight = BorderStyleEnum.THIN)
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "系统用户Excel")
public class UserExcel implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @Schema(description = "用户名称")
    @ExcelProperty(value = "用户名称")
    private String username;

    /**
     * 用户编码
     */
    @Schema(description = "用户编码")
    @ExcelProperty(value = "用户编码")
    private String userCode;

    /**
     * 登录账户名称
     */
    @Schema(description = "登录账户名称")
    @ExcelProperty(value = "登录账户名称")
    private String displayName;

    /**
     * 头像地址
     */
    @Schema(description = "头像地址")
    @ExcelProperty(value = "头像地址")
    private String avatar;

    /**
     * 岗位名称
     */
    @Schema(description = "岗位名称")
    @ExcelProperty(value = "岗位名称")
    private String postName;

    /**
     * 部门名称
     */
    @Schema(description = "部门名称")
    @ExcelProperty(value = "部门名称")
    private String deptName;

    /**
     * 性别 0 女性 1 男性
     */
    @Schema(description = "性别 0 女性 1 男性")
    @ExcelProperty(value = "性别")
    private Integer sex;

    /**
     * 身份证号
     */
    @Schema(description = "身份证号")
    @ExcelProperty(value = "身份证号")
    @SensitiveInfo(SensitiveStrategy.ID_CARD)
    @SecuredField(column = "id_card")
    private String idCard;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    @ExcelProperty(value = "手机号")
    @SensitiveInfo(SensitiveStrategy.PHONE)
    private String phone;

    /**
     * 微信OpenID
     */
    @Schema(description = "微信OpenID")
    @ExcelProperty(value = "微信OpenID")
    private String openId;

    /**
     * 邮件
     */
    @Schema(description = "邮件")
    @ExcelProperty(value = "邮件")
    @SensitiveInfo(SensitiveStrategy.EMAIL)
    private String email;

    /**
     * 锁定
     */
    @Schema(description = "锁定")
    @ExcelProperty(value = "锁定")
    private Integer isLock;

}
