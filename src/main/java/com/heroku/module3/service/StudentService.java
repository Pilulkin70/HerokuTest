package com.heroku.module3.service;

import com.heroku.module3.dao.StudentDao;
import com.heroku.module3.model.UniversityGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.model.Student;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.heroku.module3.Main.RANDOM;

public class StudentService {
    public void addStudents(UniversityGroup group, int count) {
        final StudentDao studentDao = new StudentDao();
        RandomDate randomDate = new RandomDate(LocalDate.of(1972, 1, 1),
                LocalDate.of(2000, 12, 31));
        Set<Student> studentSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            Student student = new Student("FirstName-" + RANDOM.nextInt(0, 1000),
                    "LastName-" + RANDOM.nextInt(0, 1000),
                    RANDOM.nextInt(16, 25),
                    randomDate.nextDate());
            student.setUniversityGroup(group);
            studentSet.add(student);
            studentDao.save(student);
        }
        group.setStudents(studentSet);
    }

    public static Student getRandomStudent() {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);
        final Root<Student> from = query.from(Student.class);
        query.select(from);
        List<Student> studentList = entityManager.createQuery(query).getResultList();
        return studentList.get(RANDOM.nextInt(0, studentList.size()));
    }

    public void printStudentInfo(float minGPA) {
        List<Object[]> results = new StudentDao().getStudentInfo(minGPA);
        results.forEach(result -> {
            System.out.printf("  %s%n", new StudentDao().getById((String) result[0]));
            System.out.printf("    %.2f%n", result[1]);
        });
    }

}
