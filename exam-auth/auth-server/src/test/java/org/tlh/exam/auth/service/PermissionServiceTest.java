package org.tlh.exam.auth.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.tlh.exam.auth.ExamAuthApplication;
import org.tlh.exam.auth.model.resp.PermissionRespDto;

import java.util.List;

/**
 * Created by 离歌笑tlh/hu ping on 2019/1/19
 * <p>
 * Github: https://github.com/tlhhup
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ExamAuthApplication.class)
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void savePermission() throws Exception {
        PermissionRespDto permission=new PermissionRespDto();
        permission.setName("链路监控");
        permission.setIcon("monitor_trace");
        permission.setIframe(false);
        permission.setIsActive(true);
        permission.setSort(2);
        permission.setUrl("trace");
        permission.setPermission("monitor:trace");

        permission.setParentId(5);

        this.permissionService.savePermission(permission);
    }

    @Test
    public void deletePermission() throws Exception {
    }

    @Test
    public void changePermissionStatus() throws Exception {
    }

    @Test
    public void updatePermission() throws Exception {
    }

    @Test
    public void findPermissionDetail() throws Exception {
    }

    @Test
    public void findAll() throws Exception {
        List<PermissionRespDto> menus = this.permissionService.findAll();
        System.out.println(menus);
    }

}