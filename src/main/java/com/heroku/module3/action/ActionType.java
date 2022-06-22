package com.heroku.module3.action;


public enum ActionType {
    SEARCH_GROUP_FOR_NAME("Искать группу по названию", new EmptyAction()),
    NUMBER_OF_STUDENT_IN_GROUP("Количество студентов в каждой группе", new EmptyAction()),
    GPA("Средний балл каждой группы", new EmptyAction()),
    LECTURER_BY_FIRST_AND_LAST_NAME("Преподавателя по имени или фамилии", new EmptyAction()),
    WORST_AND_BEST_PERFORMING_SUBJECT("Предмет с самой худшей и самой лучшей успеваемостью", new EmptyAction()),
    STUDENTS_WITH_GPA_ABOVE_VALUE("Студентов, чей средний балл по конкретному предмету выше заданного значения", new EmptyAction()),
    STRUCTURE("Показать структуру университета", new EmptyAction()),
    EXIT("Выход", new Exit());

    private final String name;
    private final Action action;
    ActionType(String name, Action action) {
        this.name = name;
        this.action = action;
    }

    public void doAction() {
        action.doAction();
    }

    @Override
    public String toString() {
        return name;
    }
}
