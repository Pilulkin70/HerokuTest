package com.heroku.hw25.service;

import com.heroku.hw25.dao.UniversityGroupDao;
import com.heroku.hw25.model.University;
import com.heroku.hw25.model.UniversityGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniversityGroupService {
    public void addUniversityGroup(University university, int count) {
        Set<UniversityGroup> universityGroups = new HashSet<>();
        for (int i = 0; i < count; i++) {
            UniversityGroup universityGroup = new UniversityGroup("UniversityGroup-" + i, university);
            universityGroups.add(universityGroup);
        }
        university.setUniversityGroups(universityGroups);
    }

    public static void printGroupSummary() {
        List<Object[]> resultList = new UniversityGroupDao().getGroupSummary();
        resultList.forEach(r -> System.out.printf("University: %s, Group: %s, Number of students in group: %d%n",
                r[0], r[2], r[3]));
    }
}
