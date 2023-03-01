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

package com.breeze.boot.system.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.breeze.boot.core.entity.BaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.io.Serializable;

/**
 * 系统数据权限自定义
 *
 * @author gaoweixuan
 * @date 2022-12-10
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@TableName(value = "sys_data_permission_custom")
@Schema(description = "系统数据权限自定义实体")
public class SysDataPermissionCustom extends BaseModel<SysDataPermissionCustom> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 数据权限ID
     */
    private Long dataPermissionId;

    /**
     * 比较
     */
    private String compare;

    /**
     * 字段名
     */
    private String tableColumn;

    /**
     * 运算符号
     */
    private String operator;

    /**
     * 条件
     */
    private String conditions;

}
