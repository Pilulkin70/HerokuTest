package com.heroku.hw25;
/*
Скопировать проект в свой и дополнить функционалом:
●	Course
    ○	Добавить поле сложность со случайным значением 0-10
    ○	В CourseDao, используя механизм Criteria, написать метод выбора всех курсов соответствующих диапазону сложности
    ○	Добавить метод в CourseService с возможностью отобразить в консоль курсы, полученные в результате работы CourseDao
●	Curator
    ○	Добавить поле дата рождения и стаж работы
    ○	В CuratorDao, используя механизм Criteria, написать метод выбора всех преподователей родившихся до Х даты и имеющих больше Y стажа работы
    ○	Добавить метод в CuratorService с возможностью отобразить в консоль кураторов, полученные в результате работы CuratorDao
●	Student
    ○	В StudentDao, используя механизм Native Query, достать информацию по конкретному студенту: группу, куратора, университет
    ○	Добавить метод в StudentService с возможностью отобразить в консоль информацию о студенте, полученную в результате работы CuratorDao
●	UniversityGroup
    ○	В UniversityGroupDao, используя механизм Native Query, достать информацию о количестве студентов учащихся в каждой группе
    ○	Добавить метод в UniversityGroupService с возможностью отобразить в консоль информацию о группе и количестве студентов в ней, полученную в результате работы CuratorDao
●	University
    ○	Реализовать полный фовод инфраструктуры университета в методе print, используя возможности hibernate по подгрузке зависимых обьектов

Дополнительно, по желанию:
●	Создать сервис для инфраструктуры
●	Разделить логику класса UniversityService
●	Реализовать цепочку шагов создания инфраструктуры университета
●	Добавить проверки для успешного создания
    ○	У любой сущности должно быть имя
    ○	В университете должна быть хотя бы одна группа
    ○	В группе должен быть куратор

Погуглить паттерн Builder :)
*/

import com.heroku.hw25.config.HibernateFactoryUtil;
import com.heroku.hw25.service.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;

public class Main {
    public static final Random RANDOM = new Random();
    public static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final InfrastructureService infrastructureService = new InfrastructureService();

    public static void main(String[] args) {
        HibernateFactoryUtil.init();
        infrastructureService.createUniversitiesWithInfrastructure();
        infrastructureService.printUniversityInfrastructure();
        final Integer minValue = 1;
        final Integer maxValue = 4;
        System.out.printf("List of courses with complexity in range from %d to %d:%n", minValue, maxValue);
        CourseService.printCourseWithComplexityInRange(minValue, maxValue);
        final Date upToDate = java.sql.Date.valueOf(LocalDate.of(1999, 1, 1));
        final Double minExperience = 4.2;
        System.out.printf("\nList of curators born before %s and with more than %.2f experience:%n",
                new SimpleDateFormat(DATE_FORMAT).format(upToDate), minExperience);
        CuratorService.printCuratorBornBeforeAndWithExperienceGreatThan(upToDate, minExperience);
        System.out.println("\nRandom student info:");
        StudentService.printStudentInfo(StudentService.getRandomStudent());
        System.out.println("\nGroups summary:");
        UniversityGroupService.printGroupSummary();
    }
}