package com.heroku.hw25.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.heroku.hw25.config.HibernateFactoryUtil;
import com.heroku.hw25.dao.CuratorDao;
import com.heroku.hw25.dao.StudentDao;
import com.heroku.hw25.dao.UniversityDao;
import com.heroku.hw25.dao.UniversityGroupDao;
import com.heroku.hw25.model.Student;
import com.heroku.hw25.model.UniversityGroup;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.heroku.hw25.Main.RANDOM;

public class StudentService {
    public void addStudents(UniversityGroup group, int count) {
        Set<Student> studentSet = new HashSet<>();
        for (int i = 0; i < count; i++) {
            Student student = new Student("Student-" + RANDOM.nextInt(0, 1000));
            student.setGroupSet(Collections.singleton(group));
            studentSet.add(student);
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

    public static void printStudentInfo(Student student) {
        Object[] result = new StudentDao().getStudentInfo(student);
        System.out.println(student);
        System.out.printf("  %s%n", new UniversityDao().getById((String) result[4]));
        System.out.printf("    %s%n", new UniversityGroupDao().getById((String) result[2]));
        System.out.printf("      %s%n", new CuratorDao().getById((String) result[3]));
    }
}
