package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlh.exam.auth.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer>{
}
