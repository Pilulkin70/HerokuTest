package com.heroku.module3.user;

import java.util.Scanner;

public class UserInputServices {
    private static final Scanner SCANNER = new Scanner(System.in);

    public int getNumberFromUser() {
        return SCANNER.nextInt();
    }

    public float getFloatFromUser() {
        return SCANNER.nextFloat();
    }

    public String getStringFromUser() {
        return SCANNER.next();
    }
}
