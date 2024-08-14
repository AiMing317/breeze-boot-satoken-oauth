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

package com.breeze.boot.modules.bpm.model.query;

import com.breeze.boot.core.base.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

/**
 * 流程定义查询参数
 *
 * @author gaoweixuan
 * @since 2023-03-06
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Schema(description = "流程定义查询参数")
public class BpmDefinitionQuery extends PageQuery {

    @Schema(description = "流程定义KEY")
    private String definitionKey;

    @Schema(description = "业务Key")
    private String businessKey;

    @Schema(description = "租户ID")
    private String tenantId;

}
