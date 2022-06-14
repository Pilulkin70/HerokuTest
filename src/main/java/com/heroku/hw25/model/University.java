package com.heroku.hw25.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class University implements CommonInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;

    @OneToMany(mappedBy = "university", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<UniversityGroup> universityGroups;

    public University(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "University{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getAddition() {
        return "";
    }
}
