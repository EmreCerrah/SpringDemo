package com.springDemo.demo.student;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Entity (name = "Student")
@Table (name = "student", uniqueConstraints = { @UniqueConstraint( name = "studenr_email_unique", columnNames = "email")})
public class Student {
                // Database de tutulan sinif
    @Getter
    @Setter
    @Id
    @SequenceGenerator(
            name="student_sequance",
            sequenceName ="student_sequance",
            allocationSize=1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequance"
    )
    @Column(name = "id", updatable = false)
    private long id;

    @Getter
    @Setter
    @Column(name = "name",nullable = false, columnDefinition = "TEXT")
    private String name;

    @Getter
    @Setter
    @Column(name = "email",nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Getter
    @Setter
    @Column(name = "birth_year",columnDefinition = "INTEGER")
    int birthYear;

    // Transent >> database'de saklamasina gerek olmayan veriler icin kullanilir
    @Transient
    @Setter
    private int age;

    public Student() {
    }
    public Student(long id, String name, String email, int birthYear) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthYear= birthYear;
    }

    public Student(String name, String email, int birthYear) {
        this.name = name;
        this.email = email;
        this.birthYear= birthYear;
    }

    public int getAge(){
    return (LocalDateTime.now().getYear()-birthYear);
    }
}
