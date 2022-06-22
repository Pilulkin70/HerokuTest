package com.heroku.module3.dao;

import com.heroku.module3.model.Grade;

public class GradeDao extends AbstractDao{
    @Override
    protected void init() {
        aClass = Grade.class;
    }
}
