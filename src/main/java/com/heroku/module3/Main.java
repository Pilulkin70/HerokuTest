package com.heroku.module3;
/*
Написать систему управления деятельности университета на базе Hibernate
Система должна хранить данные о:
●	Студент (имя, фамилия, возраст, дата поступления)
●	Группа (название, студенты)
●	Преподаватель (имя, фамилия, возраст, предмет)
●	Предмет (код название)
●	Оценка (студент, предмет, значение)

Сгенерировать первичные данные для работы системы. На 10 - через утилиты миграции liquibase или flyway.
Написать консольное меню для взаимодействия с системой:
●	Поиск групп по названию (не строгое совпадение, т.е. ввод ТК выдаст 1ТК-31, 2ЛТК-2)
●	Количество студентов в каждой группе
●	Средний балл каждой группы
●	Преподавателя по имени или фамилии
●	Предмет с самой худшей и самой лучшей успеваемостью
●	Студентов чей средний балл по конкретному предмету выше заданного значения

На 10 - Приложение задеплоить на любой облачный сервис
*/

import com.heroku.module3.action.ActionType;
import com.heroku.module3.config.FlywayUtil;
import com.heroku.module3.config.HibernateFactoryUtil;
import com.heroku.module3.user.UserInputServices;

public class Main {
    public static final int MIN_GRADE = 1;
    public static final int MAX_GRADE = 5;
    public static final UserInputServices INPUT_SERVICES = new UserInputServices();
    public static final String DATE_FORMAT = "dd-MM-yyyy";

    public static void main(String[] args) {
        HibernateFactoryUtil.init();
        FlywayUtil.migrate();
        while (true) {
            doAction();
        }
    }

    private static void doAction() {
        final ActionType[] values = ActionType.values();
        int number;
        do {
            System.out.println("Choose your action: ");
            for (int i = 0; i < values.length; i++) {
                System.out.printf("%d) %s%n", i, values[i]);
            }
            number = INPUT_SERVICES.getNumberFromUser();
        } while (number < 0 || number >= values.length);
        final ActionType action = values[number];
        action.doAction();
    }
}