package org.tlh.exam.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tlh.exam.user.entity.Admin;

/**
 * Created by 离歌笑tlh/hu ping on 2019/2/6
 * <p>
 * Github: https://github.com/tlhhup
 */
public interface UserRepository extends JpaRepository<Admin,Integer>{
}
