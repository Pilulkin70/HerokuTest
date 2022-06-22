package com.heroku.module3.action;

public class Exit implements Action {
    @Override
    public void doAction() {
        System.out.println("Have a nice Java!");
        System.exit(0);
    }
}
