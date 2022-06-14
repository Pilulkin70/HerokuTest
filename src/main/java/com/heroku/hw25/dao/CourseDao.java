package com.heroku.hw25.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import com.heroku.hw25.config.HibernateFactoryUtil;
import com.heroku.hw25.model.Course;

import java.util.List;

public class CourseDao extends AbstractDao<Course> {
    @Override
    protected void init() {
        aClass = Course.class;
    }

    public List<Course> getCourseWithComplexityInRange(Integer minComplexity, Integer maxComplexity) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Course> query = criteriaBuilder.createQuery(aClass);
        final Root<Course> from = query.from(aClass);
        query.select(from).where(criteriaBuilder.between(from.get("complexity"), minComplexity, maxComplexity));
        return entityManager.createQuery(query).getResultList();
    }
}
