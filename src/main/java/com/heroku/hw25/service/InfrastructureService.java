package com.heroku.hw25.service;

import com.heroku.hw25.dao.UniversityDao;
import com.heroku.hw25.model.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.heroku.hw25.Main.RANDOM;
import static com.heroku.hw25.service.AnsiColorConstants.*;
import static com.heroku.hw25.service.AnsiColorConstants.ANSI_RESET;

public class InfrastructureService {
    private final UniversityService universityService = new UniversityService();
    private final UniversityGroupService universityGroupService = new UniversityGroupService();
    private final StudentService studentService = new StudentService();
    private final CuratorService curatorService = new CuratorService();
    private final CourseService courseService = new CourseService();
    private final UniversityDao universityDao = new UniversityDao();
    private List<University> universities;
    private Set<UniversityGroup> universityGroups;

    public void createUniversitiesWithInfrastructure() {
        createUniversities()
                .addGroups()
                .addStudents()
                .addCurators()
                .addCourses()
                .build();
    }

    private InfrastructureService createUniversities() {
        universities = universityService.createUniversity();
        return this;
    }

    private InfrastructureService addGroups() {
        universities.forEach(university ->
                universityGroupService.addUniversityGroup(university, RANDOM.nextInt(2, 5)));
        return this;
    }

    private InfrastructureService addStudents() {
        universities.stream()
                .flatMap(university -> university.getUniversityGroups().stream())
                .forEach(group -> studentService.addStudents(group, RANDOM.nextInt(1, 4)));
        return this;
    }

    private InfrastructureService addCurators() {
        universities.forEach(university ->
                university.getUniversityGroups().forEach(curatorService::addCurator));
        return this;
    }

    private InfrastructureService addCourses() {
        universities.forEach(university ->
                university.getUniversityGroups().forEach(group ->
                        group.getStudents().forEach(student ->
                                courseService.setCourse(student, RANDOM.nextInt(0, 4)))));
        return this;
    }

    private void checkUniversities() {
        universityGroups = new HashSet<>();
        universities.forEach(university -> {
            exceptionNoName(university);
            if (university.getUniversityGroups() == null || university.getUniversityGroups().size() == 0) {
                throw new RuntimeException(String.format("University: %s has no Group(s).%n", university.getName()));
            } else {
                universityGroups.addAll(university.getUniversityGroups());
            }
        });
    }

    private void checkGroupsWithContent() {
        universityGroups.forEach(universityGroup -> {
            exceptionNoName(universityGroup);
            if (universityGroup.getCurator() == null) {
                throw new RuntimeException(String.format("Group: %s has no Curator.%n", universityGroup.getName()));
            }
            if (universityGroup.getStudents() != null && universityGroup.getStudents().size() != 0) {
                checkStudents(universityGroup.getStudents());
            }
        });
    }

    private void checkStudents(Set<Student> studentsSet) {
        studentsSet.forEach(student -> {
            exceptionNoName(student);
            if (student.getCourseSet() == null && student.getCourseSet().size() != 0) {
                checkCourses(student.getCourseSet());
            }
        });
    }

    private void checkCourses(Set<Course> coursesSet) {
        coursesSet.forEach(this::exceptionNoName);
    }

    private void build() {
        checkUniversities();
        checkGroupsWithContent();
        for (University university : universities) {
            universityDao.save(university);
        }
    }

    private <T extends CommonInterface> void exceptionNoName(T value) {
        if (value.getName() == null) {
            throw new RuntimeException(String.format("%s ID: %s is no Name.%n",
                    value.getClass().getSimpleName(), value.getId()));
        }
    }

    public void printUniversityInfrastructure() {
        universityDao.getAll().forEach(university -> {
            printEntity(0, ANSI_BLUE, university);
            Optional.ofNullable(university.getUniversityGroups())
                    .ifPresent(universityGroups -> universityGroups.forEach(universityGroup -> {
                        printEntity(1, ANSI_GREEN, universityGroup);
                        Optional.ofNullable(universityGroup.getCurator())
                                .ifPresent(curator -> printEntity(2, ANSI_CYAN, curator));
                        Optional.ofNullable(universityGroup.getStudents())
                                .ifPresent(students -> students.forEach(student -> {
                                    printEntity(2, ANSI_PURPLE, student);
                                    Optional.ofNullable(student.getCourseSet())
                                            .ifPresent(courses -> courses.forEach(course ->
                                                    printEntity(3, ANSI_WHITE, course)));
                                }));
                    }));
        });
        System.out.print(ANSI_RESET);
    }

    private <T extends CommonInterface> void printEntity(int level, AnsiColorConstants color, T value) {
        System.out.println(color + getFormattedString(level, value.getId(), value.getName(),
                value.getClass().getSimpleName(), value.getAddition()));
    }

    private String getFormattedString(int level, String id, String name, String className, String add) {
        String indent = " ".repeat(level * 3);
        return String.format(indent + "Class: %s%n" + indent + " ID: %s%n" + indent + " Name: %s%n" + indent + add,
                className, id, name, add);
    }
}
