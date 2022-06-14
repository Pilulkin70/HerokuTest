package com.heroku.hw25.service;

import com.heroku.hw25.dao.CourseDao;
import com.heroku.hw25.model.Course;
import com.heroku.hw25.model.Student;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static com.heroku.hw25.Main.RANDOM;

public class CourseService {
    private static final CourseDao courseDao = new CourseDao();

    public void setCourse(Student student, int courseCount) {
        Set<Course> courses = new HashSet<>();
        for (int i = 0; i < courseCount; i++) {
            final Course course = new Course("Course-" + RANDOM.nextInt(0, 1000),
                    RANDOM.nextInt(1, 11));
            course.setStudentSet(Collections.singleton(student));
            courses.add(course);
        }
        student.setCourseSet(courses);
    }

    public static void printCourseWithComplexityInRange(Integer minComplexity, Integer maxComplexity) {
        courseDao.getCourseWithComplexityInRange(minComplexity, maxComplexity).forEach(System.out::println);
    }
}
