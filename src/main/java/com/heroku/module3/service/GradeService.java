package com.heroku.module3.service;

import com.heroku.module3.model.Grade;
import com.heroku.module3.model.Student;

import java.util.HashSet;
import java.util.Set;

import static com.heroku.module3.Main.RANDOM;

public class GradeService {

    public void addGrade(Student student, int gradeCount) {
        Set<Grade> grades = new HashSet<>();
        for (int i = 0; i < gradeCount; i++) {
            final Grade grade = new Grade(RANDOM.nextInt(2, 6)
            );
            grade.setStudent(student);
            grades.add(grade);
        }
        student.setGradeSet(grades);
    }
}
