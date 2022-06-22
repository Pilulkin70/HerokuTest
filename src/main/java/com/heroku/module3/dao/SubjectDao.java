package com.heroku.module3.dao;

import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.model.Subject;
import jakarta.persistence.EntityManager;

import java.util.List;

public class SubjectDao extends AbstractDao {
    @Override
    protected void init() {
        aClass = Subject.class;
    }

    public List<Object[]> getSubjectMaxMinGPA() {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT subject.id, Avg(grade.value) " +
                "FROM subject " +
                "INNER JOIN grade " +
                "ON subject.id = grade.subject_id " +
                "GROUP BY subject.id " +
                "ORDER BY Avg(grade.value)";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }
}
