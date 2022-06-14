package com.heroku.hw25.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.heroku.hw25.Main.DATE_FORMAT;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Curator implements CommonInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    private String name;
    private Date dateOfBirth;
    private Double experience;

    @OneToOne
    private UniversityGroup universityGroup;

    public Curator(String name, Date dateOfBirth, Double experience) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Curator{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + formatDate() +
                ", experience=" + String.format("%.2f", experience) +
                ", universityGroup=" + universityGroup.getName() +
                '}';
    }

    private String formatDate() {
        return new SimpleDateFormat(DATE_FORMAT).format(this.dateOfBirth);
    }

    @Override
    public String getAddition() {
        return String.format(" Experience: %.2f%n", experience);
    }
}
