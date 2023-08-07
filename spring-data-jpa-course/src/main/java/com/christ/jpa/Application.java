package com.christ.jpa;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 史偕成
 * @date 2023/08/06 19:22
 **/
@RequiredArgsConstructor
@RestController
@SpringBootApplication
//@EntityScan(basePackageClasses = {})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Bean
    CommandLineRunner commandLineRunner(
            StudentRepository studentRepository,
            StudentIdCardRepository studentIdCardRepository) {
        return args -> {

            // 使用匿名内部类的方式 JPA无法识别 JPA环境就别用了
//            {{
//                setFirstName("John");
//                setLastName("Tom");
//                setAge(23);
//                setEmail("John@gmail.com");
//            }};
//            Student john = new Student();
//            john.setFirstName("John");
//            john.setLastName("Tom");
//            john.setAge(23);
//            john.setEmail("John@gmail.com");
//
//            Student ahmed = new Student();
//            ahmed.setFirstName("Ahmed");
//            ahmed.setLastName("Ali");
//            ahmed.setAge(23);
//            ahmed.setEmail("Ahmed@gmail.com");
//            studentRepository.save(john);
//            studentRepository.saveAll(List.of(john, ahmed));
//
//            // 数量
//            System.out.println(studentRepository.count());
//
//            // Lambda 表达式  ifPresentOrElse 可以用用
//            studentRepository.findById(2L)
//                    .ifPresentOrElse(System.out::println, () -> {
//                        System.out.println("student with Id 2 not found");
//                    });
//
//            List<Student> students = studentRepository.findAll();
//            students.forEach(System.out::println);
//
//            studentRepository.deleteById(1L);
//
//            System.out.println(studentRepository.count());

//            studentRepository.findStudentByEmail("Ahmed@gmail.com")
//                    .ifPresentOrElse(System.out::println, () -> {
//                        System.out.println("student with email Ahmed@gmail.com not found");
//                    });

//            studentRepository.findStudentsByFirstNameEqualsAndAgeEquals("John", 23).forEach(System.out::println);

//            PageRequest pageRequest = PageRequest.of(0, 5, Sort.by("firstName").ascending());
//            Page<Student> all = studentRepository.findAll(pageRequest);
//            System.out.println(all);

//            List<Student> students = useFakerGenerateStudent(null);
//            StudentIdCard studentIdCard = new StudentIdCard("123456789", students.get(0));
//            studentIdCardRepository.save(studentIdCard);

//            studentIdCardRepository.findById(1L)
//                    .ifPresentOrElse(System.out::println, () -> {
//                    });


            studentRepository.findById(32L).ifPresentOrElse(System.out::println, () -> {});
        };
    }

    private void sorting(StudentRepository studentRepository) {
        // sort 排序
        Sort sort = Sort.by(Sort.Direction.ASC, "firstName");
        studentRepository.findAll(sort).forEach(student -> {
            System.out.println(student.getFirstName());
        });
    }


    private List<Student> useFakerGenerateStudent(StudentRepository studentRepository) {
        //  使用 faker 工具自动添加mock数据
        List<Student> students = new ArrayList<Student>();
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            Student student = new Student();
            String firstName = faker.name().firstName();
            student.setFirstName(firstName);
            student.setLastName(faker.name().lastName());
            student.setAge(faker.number().numberBetween(17, 35));
            student.setEmail(firstName + "@gmail.com");
            students.add(student);
        }
        if (studentRepository != null) {
            studentRepository.saveAll(students);
        }
        return students;
    }
}
