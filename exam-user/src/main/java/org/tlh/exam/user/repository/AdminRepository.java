package org.tlh.exam.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import org.tlh.exam.user.entity.Admin;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
public interface AdminRepository extends JpaRepository<Admin,Integer>{

    @Query("select authId from Admin where id=?1")
    Integer findAuthIdById(int id);

    @Modifying
    @Transactional
    @Query("update Admin set isActive=?2 where id=?1")
    int updateAdminActiveById(int id, boolean active);

}
