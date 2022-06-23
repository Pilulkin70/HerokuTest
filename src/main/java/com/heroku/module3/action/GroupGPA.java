package com.heroku.module3.action;

import com.heroku.module3.dao.UniversityGroupDao;

public class GroupGPA implements Action {
    UniversityGroupDao universityGroupDao = new UniversityGroupDao();

    @Override
    public void doAction() {
        System.out.println("Grade point average (GPA) of each group");
        universityGroupDao.getGroupGPA().forEach(result -> {
            System.out.printf("Group '%s': GPA = %.2f %n", result[1], result[2]);
        });
        System.out.println();
    }
}
