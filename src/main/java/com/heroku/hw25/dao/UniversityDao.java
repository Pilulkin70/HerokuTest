package com.heroku.hw25.dao;

import com.heroku.hw25.model.University;

public class UniversityDao extends AbstractDao<University> {
    @Override
    protected void init() {
        aClass = University.class;
    }
}
