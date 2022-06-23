package com.heroku.module3.action;

import com.heroku.module3.dao.UniversityGroupDao;
import com.heroku.module3.model.UniversityGroup;

import java.util.List;

import static com.heroku.module3.Main.INPUT_SERVICES;

public class FindGroup implements Action {
    final UniversityGroupDao universityGroupDao = new UniversityGroupDao();

    @Override
    public void doAction() {
        String inputString;
        do {
            System.out.println("Input group name (or part of name): ");
            inputString = INPUT_SERVICES.getStringFromUser();
        } while (inputString.length() == 0);

        System.out.println("List of groups, contains '" + inputString + "' in name:");
        List<UniversityGroup> universityGroups = universityGroupDao.getGroupsByNamePart(inputString);
        if (universityGroups.size() == 0) {
            System.out.println("is empty (");
        } else {
            universityGroups.forEach(universityGroup -> System.out.println(universityGroup.getName()));
        }
        System.out.println();
    }
}
