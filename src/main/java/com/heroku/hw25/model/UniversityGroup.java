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
public class UniversityGroup implements CommonInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;
    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "universityGroup_student",
            joinColumns = @JoinColumn(name = "universityGroup_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id"))
    private Set<Student> students;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "curator_id")
    private Curator curator;

    public UniversityGroup(String name, University university) {
        this.name = name;
        this.university = university;
    }

    @Override
    public String toString() {
        return "UniversityGroup{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", university=" + university.getName() +
                ", number of students=" + students.size() +
                ", curator=" + curator.getName() +
                '}';
    }

    @Override
    public String getAddition() {
        return String.format(" Number of students: %d%n", students.size());
    }
}
