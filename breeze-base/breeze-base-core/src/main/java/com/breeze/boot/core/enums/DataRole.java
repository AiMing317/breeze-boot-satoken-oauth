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

package com.breeze.boot.core.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 数据角色
 *
 * @author gaoweixuan
 * @since 2023/05/25
 */
@Getter
@RequiredArgsConstructor
public enum DataRole {

    DEPT_ID("dept_id", "DEPT"),

    USER_ID("user_id", "USER");

    public static DataRole getDataRoleByType(String type) {
        for (DataRole dataRole : DataRole.values()) {
            if (dataRole.getType().equals(type)) {
                return dataRole;
            }
        }
        return null;
    }

    private final String column;
    private final String type;

}
