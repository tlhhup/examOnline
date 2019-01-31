package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{

    @Modifying
    @Transactional
    @Query(value = "delete from auth_users_roles where roles_id=?1",nativeQuery = true)
    int deleteUserRoleLinkByRoleId(int id);

}
