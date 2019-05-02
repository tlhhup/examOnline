package org.tlh.generator.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tlh.generator.dto.ResponseDto;
import org.tlh.generator.entity.DbSchema;
import org.tlh.generator.entity.Table;
import org.tlh.generator.service.GeneratorService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/5/2
 * <p>
 * Github: https://github.com/tlhhup
 */
@RestController
@RequestMapping("/code")
public class GeneratorController {

    @Autowired
    private GeneratorService generatorService;

    @GetMapping("/dbs")
    public ResponseDto<List<DbSchema>> list() {
        List<DbSchema> dbSchemas = this.generatorService.queryDbList();
        return ResponseDto.ok(dbSchemas);
    }


    @GetMapping("/tables")
    public ResponseDto<List<Table>> queryTable(@RequestParam("dbName")String dbName){
        List<Table> tables = this.generatorService.queryTable(dbName);
        return ResponseDto.ok(tables);
    }

    @PostMapping
    public void code(@RequestParam(name = "dbName") String dbName,
                     @RequestParam(name = "tables") String[] tableNames,
                     HttpServletResponse response) throws IOException {
        byte[] data = generatorService.generatorCode(dbName,tableNames);

        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
