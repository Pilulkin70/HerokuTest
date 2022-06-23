package com.heroku.module3.action;

import com.heroku.module3.dao.LecturerDao;
import com.heroku.module3.model.Lecturer;

import java.util.List;

import static com.heroku.module3.Main.INPUT_SERVICES;

public class FindLecturerByName implements Action {
    LecturerDao lecturerDao = new LecturerDao();

    @Override
    public void doAction() {
        String inputString;
        do {
            System.out.println("Input first or last name: ");
            inputString = INPUT_SERVICES.getStringFromUser();
        } while (inputString.length() == 0);

        System.out.printf("List of lectures, who first or last name is '%s'%n", inputString);
        List<Lecturer> lecturers = lecturerDao.getLecturerByFirstOrLastNane(inputString);
        if (lecturers.size() == 0) {
            System.out.println("is empty (");
        } else {
            lecturers.forEach(System.out::println);
        }
        System.out.println();
    }
}
