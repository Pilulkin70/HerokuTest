package com.heroku.module3.action;

import com.heroku.module3.dao.UniversityGroupDao;
import com.heroku.module3.model.UniversityGroup;

import java.util.List;

public class CountStudentsInGroups implements Action{
    final UniversityGroupDao universityGroupDao = new UniversityGroupDao();
    @Override
    public void doAction() {
            System.out.println("The number of students in each group");
            List<UniversityGroup> groupList = universityGroupDao.getAll();
            groupList.forEach(universityGroup -> {
                System.out.println("Group '" + universityGroup.getName() + ": Number of students = " + universityGroup.getStudents().size());
            });
            System.out.println();
    }
}
