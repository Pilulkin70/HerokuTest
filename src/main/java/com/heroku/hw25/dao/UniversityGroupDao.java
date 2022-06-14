package com.heroku.hw25.dao;

import jakarta.persistence.EntityManager;
import com.heroku.hw25.config.HibernateFactoryUtil;
import com.heroku.hw25.model.UniversityGroup;

import java.util.List;

public class UniversityGroupDao extends AbstractDao<UniversityGroup> {
    @Override
    protected void init() {
        aClass = UniversityGroup.class;
    }

    public List<Object[]> getGroupSummary() {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT university.name, universitygroup.id, universitygroup.name, Count(student.id) " +
                "FROM university " +
                "INNER JOIN (universitygroup " +
                    "INNER JOIN (student " +
                        "INNER JOIN universitygroup_student " +
                        "ON student.id = universitygroup_student.student_id) " +
                    "ON universitygroup.id = universitygroup_student.universitygroup_id) " +
                "ON university.id = universitygroup.university_id " +
                "GROUP BY university.name, universitygroup.id, universitygroup.name " +
                "ORDER BY university.name, universitygroup.name;";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }
}
