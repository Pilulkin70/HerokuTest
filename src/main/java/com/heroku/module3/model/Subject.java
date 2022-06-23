package com.heroku.module3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    String id;
    String code;
    String name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Grade> gradeSet;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecturer_id")
    Lecturer lecturer;

    public Subject(String code, String name) {
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "('" + id + '\'' +
                ", '" + code + '\'' +
                ", '" + name + '\'' +
                ", '" + lecturer.getId() + '\'' +
                ')';
    }
}
