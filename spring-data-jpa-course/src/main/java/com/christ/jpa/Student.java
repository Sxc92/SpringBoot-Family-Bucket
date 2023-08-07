package com.christ.jpa;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author 史偕成
 * @date 2023/08/06 19:43
 **/
@ToString
@Getter
@Setter
@Entity(name = "Student")
@Table(name = "student", uniqueConstraints = {@UniqueConstraint(name = "student_email_unique", columnNames = "email")})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "")
    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private Integer age;


    @OneToOne(mappedBy = "student")
    private StudentIdCard studentIdCard;

//    public Student(Long id, String firstName, String lastName, String email, String age) {
//        this.id = id;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.email = email;
//        this.age = age;
//    }
}
