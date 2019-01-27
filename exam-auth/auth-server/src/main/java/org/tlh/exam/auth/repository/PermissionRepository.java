package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Permission;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission,Integer>{

    @Modifying
    @Transactional
    @Query("update Permission set isActive=?2 where id=?1")
    int updatePermissionStatus(int id,boolean active);

    List<Permission> findByParentId(int pId);

    @Modifying
    @Transactional
    @Query(value = "delete from auth_roles_permissions where permissions_id=?1",nativeQuery = true)
    int deleteRoleByPermissionId(int id);

}
