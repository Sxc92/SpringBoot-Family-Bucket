package com.christ.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author 史偕成
 * @date 2023/08/06 20:06
 **/
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     *
     * @param email 电子邮件
     * @return
     */
    Optional<Student> findStudentByEmail(String email);


    List<Student> findStudentsByFirstNameEqualsAndAgeEquals(String firstName, Integer age);
}
