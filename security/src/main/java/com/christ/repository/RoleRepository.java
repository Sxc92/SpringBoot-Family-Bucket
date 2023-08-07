package com.christ.repository;

import com.christ.entity.Role;
import com.christ.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 史偕成
 * @date 2023/08/04 17:19
 **/
@Repository
public interface RoleRepository extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    @Query(value = "select r.* from role r left join user_role ur on r.role_id = ur.role_id where ur.user_id = ?1", nativeQuery = true)
    List<Role> findByUserId(Long userId);
}
