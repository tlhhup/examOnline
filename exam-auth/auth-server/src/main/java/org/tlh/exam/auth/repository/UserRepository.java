package org.tlh.exam.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.auth.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer>{

    @Modifying
    @Transactional
    @Query("update User set password=?2 where id=?1")
    int updatePasswordById(int id, String password);

    @Modifying
    @Transactional
    @Query("update User set isActive=?2 where id=?1")
    int updateUserActiveById(int id, boolean active);

    Optional<User> findOneByUserNameAndUserType(String userName,int userType);
}
