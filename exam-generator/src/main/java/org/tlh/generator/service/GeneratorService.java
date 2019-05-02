package org.tlh.generator.service;


import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tlh.generator.entity.DbSchema;
import org.tlh.generator.entity.Table;
import org.tlh.generator.mapper.GeneratorMapper;
import org.tlh.generator.utils.GeneratorUtils;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 * <p>
 * Created by 离歌笑tlh/hu ping on 2019/5/2
 * <p>
 * Github: https://github.com/tlhhup
 */
@Service
public class GeneratorService {

    @Autowired
    private GeneratorMapper generatorMapper;

    public List<DbSchema> queryDbList() {
        List<DbSchema> dbSchemas = this.generatorMapper.queryDb();
        return dbSchemas;
    }

    public List<Table> queryTable(String dbName) {
        List<Table> tables = this.generatorMapper.queryTable(dbName);
        return tables;
    }

    public List<Map<String, String>> queryColumns(String dbName,String tableName) {
        return generatorMapper.queryColumns(dbName,tableName);
    }

    public byte[] generatorCode(String dbName,String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : tableNames) {
            //查询表信息
            Table table = this.generatorMapper.queryTableDetail(dbName,tableName);
            //查询列信息
            List<Map<String, String>> columns = queryColumns(dbName,tableName);
            //生成代码
            GeneratorUtils.generatorCode(table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
