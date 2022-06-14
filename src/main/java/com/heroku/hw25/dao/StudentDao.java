package com.heroku.hw25.dao;

import jakarta.persistence.EntityManager;
import com.heroku.hw25.config.HibernateFactoryUtil;
import com.heroku.hw25.model.Student;

public class StudentDao extends AbstractDao<Student> {
    @Override
    protected void init() {
        aClass = Student.class;
    }

    public Object[] getStudentInfo(Student student) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT student.id, student.name, universitygroup.id, curator.id, university.id " +
                "FROM university " +
                "INNER JOIN (curator " +
                    "INNER JOIN (universitygroup " +
                        "INNER JOIN (student " +
                            "INNER JOIN universitygroup_student " +
                            "ON student.id = universitygroup_student.student_id) " +
                        "ON universitygroup.id = universitygroup_student.universitygroup_id) " +
                    "ON curator.id = universitygroup.curator_id) " +
                "ON university.id = universitygroup.university_id " +
                "WHERE student.id='" + student.getId() + "'";
        return (Object[]) entityManager.createNativeQuery(sqlQuery).getSingleResult();
    }
}
