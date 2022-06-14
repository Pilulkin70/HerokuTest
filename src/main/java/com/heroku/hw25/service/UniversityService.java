package com.heroku.hw25.service;

import com.heroku.hw25.model.University;

import java.util.Arrays;
import java.util.List;

public class UniversityService {
    public List<University> createUniversity() {
        final University university1 = new University("university1");
        final University university2 = new University("university2");
        return Arrays.asList(university1, university2);
    }
}
