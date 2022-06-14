package com.heroku.hw25.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import com.heroku.hw25.config.HibernateFactoryUtil;
import com.heroku.hw25.model.Curator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CuratorDao extends AbstractDao<Curator> {
    @Override
    protected void init() {
        aClass = Curator.class;
    }

    public List<Curator> getCuratorBornBeforeAndWithExperienceGreatThan(Date maxDataBorn, Double minExperience) {
        final EntityManager entityManager = HibernateFactoryUtil.getEntityManager();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Curator> query = criteriaBuilder.createQuery(aClass);
        final Root<Curator> from = query.from(aClass);
        List<Predicate> predicates = new ArrayList<>(2);
        if (maxDataBorn != null) {
            predicates.add(criteriaBuilder.lessThan(from.get("dateOfBirth"), maxDataBorn));
        }
        if (minExperience != null) {
            predicates.add(criteriaBuilder.greaterThan(from.get("experience"), minExperience));
        }
        query.select(from).where(predicates.toArray(new Predicate[]{}));
        return entityManager.createQuery(query).getResultList();
    }
}
