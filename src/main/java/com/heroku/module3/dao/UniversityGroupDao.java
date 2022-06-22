package com.heroku.module3.dao;

import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.model.UniversityGroup;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class UniversityGroupDao extends AbstractDao {
    @Override
    protected void init() {
        aClass = UniversityGroup.class;
    }

    public List<UniversityGroup> getGroupsByNamePart(String namePart) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<UniversityGroup> query = criteriaBuilder.createQuery(aClass);
        final Root<UniversityGroup> from = query.from(aClass);
        query.select(from);
        query.where(criteriaBuilder.like(
                criteriaBuilder.upper(from.get("name")),
                "%" + namePart.toUpperCase() + "%"));
        return entityManager.createQuery(query).getResultList();
    }

    public List<Object[]> getGroupGPA() {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final String sqlQuery = "SELECT universityGroup.id, universityGroup.name, Avg(grade.value) " +
                "FROM (universityGroup " +
                "INNER JOIN student " +
                "ON universityGroup.id = student.universitygroup_id) " +
                "INNER JOIN grade " +
                "ON student.id = grade.student_id " +
                "GROUP BY universityGroup.id, universityGroup.name " +
                "ORDER BY universityGroup.name;";
        return entityManager.createNativeQuery(sqlQuery).getResultList();
    }
}
