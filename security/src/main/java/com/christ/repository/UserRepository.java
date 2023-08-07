package com.christ.repository;

import com.christ.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author 史偕成
 * @date 2023/08/04 17:16
 **/
@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名称查询
     *
     * @param username
     * @return
     */
    User findUserByUsername(String username);
}
