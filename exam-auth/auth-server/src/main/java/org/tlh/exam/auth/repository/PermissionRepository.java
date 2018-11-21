package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlh.exam.auth.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission,Integer>{
}
