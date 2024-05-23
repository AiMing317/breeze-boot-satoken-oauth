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

package com.breeze.boot.gen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.breeze.boot.gen.domain.Column;
import com.breeze.boot.gen.domain.Table;
import com.breeze.boot.gen.domain.param.TableParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * mysql映射器
 *
 * @author gaoweixuan
 * @since 2023/07/03
 */
@Mapper
public interface DbMapper {

    Page<Table> listPage(@Param("page") Page<Table> page, @Param("tableParam") TableParam tableParam);

    Table getTableInfo(@Param("TableName") String TableName);

    List<Column> listTableColumn(@Param("tableName") String tableName);

}
