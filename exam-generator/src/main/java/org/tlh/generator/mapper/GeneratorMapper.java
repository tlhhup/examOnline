package org.tlh.generator.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.tlh.generator.entity.DbSchema;
import org.tlh.generator.entity.Table;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * Created by 离歌笑tlh/hu ping on 2019/5/2
 * <p>
 * Github: https://github.com/tlhhup
 */
@Mapper
public interface GeneratorMapper {

    List<DbSchema> queryDb();

    List<Table> queryTable(String dbName);

    Table queryTableDetail(@Param("dbName") String dbName,@Param("tableName") String tableName);

    List<Map<String, String>> queryColumns(@Param("dbName") String dbName,@Param("tableName") String tableName);
}
