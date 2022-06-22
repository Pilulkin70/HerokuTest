package com.heroku.module3.service;

import com.heroku.module3.dao.StudentDao;
import com.heroku.module3.dao.UniversityGroupDao;
import com.heroku.module3.model.UniversityGroup;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UniversityGroupService {
    private final UniversityGroupDao universityGroupDao = new UniversityGroupDao();

    public Set<UniversityGroup> addUniversityGroup(int count) {
        Set<UniversityGroup> universityGroups = new HashSet<>();
        for (int i = 0; i < count; i++) {
            UniversityGroup universityGroup = new UniversityGroup("UniversityGroup-" + i);
            universityGroups.add(universityGroup);
            universityGroupDao.save(universityGroup);
        }
        return universityGroups;
    }

    public void printGroupsByNamePart(String namePart) {
        universityGroupDao.getGroupsByNamePart(namePart)
                .forEach(universityGroup -> System.out.println(universityGroup.getName()));
    }

    public void printNumberOfStudentsInGroup() {
        List<UniversityGroup> groupList = universityGroupDao.getAll();
        groupList.forEach(universityGroup -> {
            System.out.println(universityGroup.getName() + ": Number of students=" + universityGroup.getStudents().size());
        });
    }

    public void printGroupGPA() {
        universityGroupDao.getGroupGPA().forEach(result -> {
            System.out.printf("%s: GPA=%.2f %n", result[1], result[2]);
        });
    }
}
