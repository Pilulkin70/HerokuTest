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

    public List<Object[]> getStudentInfo(String subjectId, float minGPA) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT student.id, Avg(grade.value) " +
                "FROM student " +
                "INNER JOIN grade " +
                "ON student.id = grade.student_id " +
                "WHERE subject_id = '" + subjectId + "' " +
                "GROUP BY student.id " +
                "HAVING Avg(grade.value)>" + minGPA +
                "";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }
}
