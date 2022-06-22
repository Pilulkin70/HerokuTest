package com.heroku.module3.dao;

import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.model.Lecturer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class LecturerDao extends AbstractDao {
    @Override
    protected void init() {
        aClass = Lecturer.class;
    }

    public List<Lecturer> getLecturerByFirstOrLastNane(String name) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Lecturer> query = criteriaBuilder.createQuery(aClass);
        final Root<Lecturer> from = query.from(aClass);
        final Predicate predicateFirstName =
                criteriaBuilder.equal(criteriaBuilder.upper(from.get("firstNme")), name.toUpperCase());
        final Predicate predicateLastName =
                criteriaBuilder.equal(criteriaBuilder.upper(from.get("lastNme")), name.toUpperCase());
        query.select(from).where(criteriaBuilder.or(predicateFirstName, predicateLastName));
        return entityManager.createQuery(query).getResultList();
    }
}
