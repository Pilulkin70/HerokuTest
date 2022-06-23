package com.heroku.module3.action;


public enum ActionType {
    SEARCH_GROUP_FOR_NAME("Search group by name",
            new FindGroup()),
    NUMBER_OF_STUDENT_IN_GROUP("Count the number of students in each group",
            new CountStudentsInGroups()),
    GPA("Grade point average (GPA) of each group",
            new GroupGPA()),
    LECTURER_BY_FIRST_AND_LAST_NAME("Find a lecturer by first or last name",
            new FindLecturerByName()),
    WORST_AND_BEST_PERFORMING_SUBJECT("The subject with the worst and best performance",
            new SubjectsWithMinAndMaxGPA()),
    STUDENTS_WITH_GPA_ABOVE_VALUE("Students whose GPA in a particular subject is above a given value",
            new StudentsWithGPAMoreThenValue()),
    EXIT("Exit",
            new Exit());

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
