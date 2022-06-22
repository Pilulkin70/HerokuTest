package com.heroku.module3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import static com.heroku.module3.Main.DATE_FORMAT;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String firstName;
    private String lastName;
    private int age;
    private Date dateOfAdmission;

    @ManyToOne
    @JoinColumn(name = "universityGroup_id")
    private UniversityGroup universityGroup;

    @OneToMany(mappedBy = "student", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Grade> gradeSet;

    public Student(String firstName, String lastName, int age, Date dateOfAdmission) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.dateOfAdmission = dateOfAdmission;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + this.id + '\'' +
                ", first name='" + this.firstName + '\'' +
                ", last name='" + this.lastName + '\'' +
                ", age=" + this.age +
                ", dateOfAdmission=" + formatDate() +
                '}';
    }

    private String formatDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(this.dateOfAdmission);
    }
}
