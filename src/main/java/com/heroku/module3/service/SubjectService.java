package com.heroku.module3.service;

import com.heroku.module3.dao.SubjectDao;
import com.heroku.module3.model.Subject;

import java.util.*;

import static com.heroku.module3.Main.RANDOM;

public class SubjectService {
    final SubjectDao subjectDao = new SubjectDao();

    public void addSubjects(int courseCount) {

        for (int i = 0; i < courseCount; i++) {
            final Subject subject = new Subject("" + RANDOM.nextInt(255), "Discipline-" + RANDOM.nextInt(0, 1000)
            );
            subjectDao.save(subject);
        }
    }

    public void printSubjectMinMaxGPA() {
        List<Object[]> results = subjectDao.getSubjectMaxMinGPA();
        ListIterator<Object[]> resultsIterator = results.listIterator();

        while (resultsIterator.hasNext()) {
            System.out.println(resultsIterator.next()[1]);
        }
    }
}
