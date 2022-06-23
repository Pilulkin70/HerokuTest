package com.heroku.module3.user;

import com.heroku.module3.action.ActionType;

import static com.heroku.module3.Main.INPUT_SERVICES;

public class MainMenu {
    public static void doAction() {
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
