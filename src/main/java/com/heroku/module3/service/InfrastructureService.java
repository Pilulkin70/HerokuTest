package com.heroku.module3.service;

import com.heroku.module3.dao.GradeDao;
import com.heroku.module3.dao.SubjectDao;
import com.heroku.module3.model.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.heroku.module3.Main.RANDOM;

public class InfrastructureService {
    private final UniversityGroupService universityGroupService = new UniversityGroupService();
    private final StudentService studentService = new StudentService();
    private final LecturerService lecturerService = new LecturerService();
    private final GradeService gradeService = new GradeService();
    private final SubjectService subjectService = new SubjectService();

    private Set<UniversityGroup> universityGroups;
    private final SubjectDao subjectDao = new SubjectDao();
    private final GradeDao gradeDao = new GradeDao();


    public void createUniversitiesWithInfrastructure() {
        createUniversities()
                .addGroups()
                .addStudents()
                .addGrades()
                .addSubjects()
                .setSubjects();

        universityGroupService.printGroupsByNamePart("p-1");
        System.out.println();
        universityGroupService.printNumberOfStudentsInGroup();
        System.out.println();
        universityGroupService.printGroupGPA();
        System.out.println();
        subjectService.printSubjectMinMaxGPA();
        System.out.println();
        studentService.printStudentInfo(3.1f);

    }

    private InfrastructureService createUniversities() {
        return this;
    }

    private InfrastructureService addGroups() {
        universityGroups = universityGroupService.addUniversityGroup(RANDOM.nextInt(2, 5));
        return this;
    }

    private InfrastructureService addStudents() {
        universityGroups.forEach(group -> studentService.addStudents(group, RANDOM.nextInt(2, 5)));
        return this;
    }

    private InfrastructureService addGrades() {
        universityGroups.forEach(group -> group.getStudents()
                .forEach(student -> {
                    gradeService.addGrade(student, RANDOM.nextInt(1, 6));
                }));
        return this;
    }

    private InfrastructureService addSubjects() {
        subjectService.addSubjects(RANDOM.nextInt(5, 10));
        return this;
    }

    private InfrastructureService setSubjects() {
        final List<Subject> subjectList = subjectDao.getAll();
        subjectList.forEach(subject -> {
            lecturerService.addLecturers(subject);
            subjectDao.save(subject);
        });
        universityGroups.forEach(group -> group.getStudents()
                .forEach(student -> student.getGradeSet().forEach(grade -> {
                    final Subject subject = subjectList.get(RANDOM.nextInt(0, subjectList.size()));
                    subject.setGradeSet(Collections.singleton(grade));
                    subjectDao.save(subject);
                    grade.setSubject(subject);
                    gradeDao.save(grade);
                })));
        return this;
    }


    private <T extends CommonInterface> void exceptionNoName(T value) {
        if (value.getName() == null) {
            throw new RuntimeException(String.format("%s ID: %s is no Name.%n",
                    value.getClass().getSimpleName(), value.getId()));
        }
    }

/*    public void printUniversityInfrastructure() {
        universityDao.getAll().forEach(university -> {
            printEntity(0, ANSI_BLUE, university);
            Optional.ofNullable(university.getUniversityGroupOlds())
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
    }*/
}
