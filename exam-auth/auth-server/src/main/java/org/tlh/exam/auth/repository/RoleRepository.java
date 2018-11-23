package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{

    @Modifying
    @Transactional
    @Query("update Role set roleName=:#{#role.roleName},roleValue=:#{#role.roleValue},description=:#{#role.description},isActive=:#{#role.isActive} where id=:#{#role.id}")
    int updateRole(@Param("role") Role role);
}
