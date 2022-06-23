package com.heroku.module3.dao;

import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.model.Subject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SubjectDao extends AbstractDao<Subject> {
    @Override
    protected void init() {
        aClass = Subject.class;
    }

    public List<Object[]> getSubjectsWithMaxGPA() {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT grade.subject_id, Avg(grade.value) " +
                "FROM grade " +
                "GROUP BY grade.subject_id " +
                "HAVING Avg(grade.value) >= ALL(SELECT Avg(value) FROM grade GROUP BY subject_id)";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }

    public List<Object[]> getSubjectsWithMinGPA() {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT grade.subject_id, Avg(grade.value) " +
                "FROM grade " +
                "GROUP BY grade.subject_id " +
                "HAVING Avg(grade.value) <= ALL(SELECT Avg(value) FROM grade GROUP BY subject_id)";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }
}
