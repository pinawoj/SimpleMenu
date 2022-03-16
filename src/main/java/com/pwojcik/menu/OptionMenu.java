package com.pwojcik.menu;

import java.util.ArrayList;


public class OptionMenu extends Menu {

    @Override
    protected void createOptions() {

        options = new ArrayList<>();

        this.options.add(new Option(1, "Graphic", n -> {
            System.out.println("Chosen option: " + n + "\nGraphic: ...");
            return this;
        }));
        this.options.add(new Option(2, "Sound", n -> {
            System.out.println("Chosen option: " + n + "\nSound: ...");
            return this;
        }));
        this.options.add(new Option(3, "Back", n -> {
            System.out.println("Chosen option: " + n);
            return new MainMenu();
        }));

    }

}
