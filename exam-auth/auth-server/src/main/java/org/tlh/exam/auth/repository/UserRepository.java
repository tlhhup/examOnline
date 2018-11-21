package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlh.exam.auth.entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{
}
