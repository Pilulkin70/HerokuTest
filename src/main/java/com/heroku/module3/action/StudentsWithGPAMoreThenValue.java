package com.heroku.module3.action;

import com.heroku.module3.dao.StudentDao;
import com.heroku.module3.dao.SubjectDao;
import com.heroku.module3.model.Subject;

import java.util.ArrayList;
import java.util.List;

import static com.heroku.module3.Main.*;

public class StudentsWithGPAMoreThenValue implements Action {
    final StudentDao studentDao = new StudentDao();
    final SubjectDao subjectDao = new SubjectDao();

    @Override
    public void doAction() {
        final Subject subject = selectSubject();
        float minValue = inputValue();

        System.out.printf("List of students, who GPA in '%s' is above %.2f%n", subject.getName(), minValue);
        List<Object[]> results = studentDao.getStudentInfo(subject.getId(), minValue);
        if (results.size() == 0) {
            System.out.println("is empty (");
        } else {
            results.forEach(result -> {
                System.out.printf("  %s%n", studentDao.getById((String) result[0]));
                System.out.printf("    %.2f%n", result[1]);
            });
        }
        System.out.println();
    }

    private float inputValue() {
        float value;
        do {
            System.out.println("Input min value of GPA: ");
            value = INPUT_SERVICES.getFloatFromUser();
        } while (value < MIN_GRADE || value > MAX_GRADE);
        return value;
    }

    private Subject selectSubject() {
        final ArrayList<Subject> subjects = new ArrayList<Subject>(subjectDao.getAll());
        int number;
        do {
            System.out.println("Choose an subject: ");
            for (int i = 0; i < subjects.size(); i++) {
                System.out.printf("%d) %s%n", i, subjects.get(i).getName());
            }
            number = INPUT_SERVICES.getNumberFromUser();
        } while (number < 0 || number >= subjects.size());
        return subjects.get(number);
    }
}
