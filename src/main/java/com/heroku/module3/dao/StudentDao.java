package com.heroku.module3.dao;

import jakarta.persistence.EntityManager;
import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.model.Student;

import java.util.List;

public class StudentDao extends AbstractDao<Student> {
    @Override
    protected void init() {
        aClass = Student.class;
    }

    public List<Object[]> getStudentInfo(float minGPA) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
/*        final String sqlQuery = "SELECT student.id, student.firstName, student.lastName, student.age, student.dateOfEntrance, Avg(grade.value) AS value_avg " +
                "FROM student " +
                "INNER JOIN grade ON student.id = grade.student_id " +
                "GROUP BY student.id, student.firstName, student.lastName, student.age, student.dateOfEntrance " +
                "HAVING Avg(grade.value)>" + minGPA +
                " ORDER BY student.firstName, student.lastName";*/
        final String sqlQuery = "SELECT student.id, Avg(grade.value) " +
                "FROM student " +
                "INNER JOIN grade " +
                "ON student.id = grade.student_id " +
                "GROUP BY student.id " +
                "HAVING Avg(grade.value)>" + minGPA +
                "";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }
}
