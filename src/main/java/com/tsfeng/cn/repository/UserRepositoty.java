package com.tsfeng.cn.repository;

import com.tsfeng.cn.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author tsfeng
 * @version 创建时间 2017/10/22 11:55
 */
@Repository
public interface UserRepositoty extends JpaRepository<User, Long>{

    /**
     * 根据name查找User
     * @param id id
     * @return User
     */
    @Query("select t from User t where t.id = :id")
    User findByUserId(@Param("id") Long id);
}
