package com.heroku.module3.action;

import com.heroku.module3.dao.SubjectDao;

import java.util.List;

public class SubjectsWithMinAndMaxGPA implements Action {
    SubjectDao subjectDao = new SubjectDao();

    @Override
    public void doAction() {
        List<Object[]> results = subjectDao.getSubjectsWithMaxGPA();
        results.forEach(result -> System.out.printf("Subject '%s' has maximum GPA = %.2f from all subjects.%n",
                subjectDao.getById((String) result[0]).getName(), result[1]));
        results = subjectDao.getSubjectsWithMinGPA();
        results.forEach(result -> System.out.printf("Subject '%s' has minimum GPA = %.2f from all subjects.%n",
                subjectDao.getById((String) result[0]).getName(), result[1]));
        System.out.println();
    }
}
