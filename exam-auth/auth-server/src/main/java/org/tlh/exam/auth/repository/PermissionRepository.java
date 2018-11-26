package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Integer>{

    @Modifying
    @Transactional
    @Query("update Permission set isActive=?2 where id=?1")
    int updatePermissionStatus(int id,boolean active);

}
