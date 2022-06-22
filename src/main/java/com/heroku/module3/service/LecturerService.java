package com.heroku.module3.service;

import com.heroku.module3.dao.LecturerDao;
import com.heroku.module3.model.Lecturer;
import com.heroku.module3.model.Subject;

import static com.heroku.module3.Main.RANDOM;

public class LecturerService {
    public void addLecturers(Subject subject) {
        final LecturerDao lecturerDao = new LecturerDao();
            Lecturer lecturer = new Lecturer("FirstName-" + RANDOM.nextInt(0,100),"LastName-",RANDOM.nextInt(23, 65));
            subject.setLecturer(lecturer);
            lecturer.setSubject(subject);
            lecturerDao.save(lecturer);
    }
}
