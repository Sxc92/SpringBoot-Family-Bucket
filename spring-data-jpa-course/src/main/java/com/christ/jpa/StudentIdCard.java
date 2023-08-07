package com.christ.jpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author 史偕成
 * @date 2023/08/06 21:31
 **/
//@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity(name = "StudentIdCard")
@Table(name = "student_id_card",
        uniqueConstraints = {@UniqueConstraint(name = "student_id_card_number_unique", columnNames = "card_number")})
public class StudentIdCard {
    @Id
    @SequenceGenerator(
            name = "student_card_id_sequence",
            sequenceName = "student_card_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_card_id_sequence")
    private Long id;

    @Column(name = "card_number", nullable = false, length = 15)
    private String cardNumber;

    @OneToOne(
            // 及联操作 设置
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "student_id_fk"
            )
    )
    private Student student;

    public StudentIdCard(String cardNumber, Student student) {
        this.cardNumber = cardNumber;
        this.student = student;
    }


}
