package com.heroku.module3.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class UniversityGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    private String name;

    @OneToMany(mappedBy = "universityGroup", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Student> students;

    public UniversityGroup(String name) {
        this.name = name;
    }
}
