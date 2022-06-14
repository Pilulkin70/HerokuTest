package com.heroku.hw25.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Course implements CommonInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private Integer complexity;

    @ManyToMany(mappedBy = "courseSet")
    private Set<Student> studentSet;

    public Course(String name, Integer complexity) {
        this.name = name;
        this.complexity = complexity;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", complexity=" + complexity +
                ", number of students=" + studentSet.size() +
                '}';
    }

    @Override
    public String getAddition() {
        return String.format(" Complexity: %d%n", complexity);
    }
}
