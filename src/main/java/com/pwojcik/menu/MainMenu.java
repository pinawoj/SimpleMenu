package com.pwojcik.menu;

import java.util.ArrayList;


public class MainMenu extends Menu {

    @Override
    protected void createOptions() {

        options = new ArrayList<>();

        this.options.add(new Option(1, "Start Game", n -> {
            System.out.println("Chosen option: " + n + "\nPrepare for new game...");
            return this;
        }));
        this.options.add(new Option(2, "Options", n -> {
            System.out.println("Chosen option: " + n);
            return new OptionMenu();
        }));
        this.options.add(new Option(3, "Credits", n -> {
            System.out.println("Chosen option: " + n + "\nDevelopers: Paulina Woj");
            return this;
        }));
        this.options.add(new Option(4, "Exit", n -> {
            System.out.println("Chosen option: " + n);
            return null;
        }));

    }

}
