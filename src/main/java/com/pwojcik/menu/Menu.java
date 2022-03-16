package com.pwojcik.menu;


import java.util.ArrayList;
import java.util.List;

public abstract class Menu {

    protected List<Option> options = new ArrayList<>();

    protected Menu() {
        createOptions();
    }

    protected abstract void createOptions();

    public final Menu runOption(int optionId) {
        Option option = options.get(optionId);
        return option.getAction().apply(option.getDescription());
    }

    public final void display() {
        System.out.println("\n[  " + this.getClass().getSimpleName() + "  ]");
        System.out.println("===============");
        for (Option option : this.options) {
            System.out.println(option.getId() + ". " + option.getDescription());
        }
        System.out.println("===============");
        System.out.println("Choose option: ");
    }

    public final List<Option> getOptions() {
        return this.options;
    }

}
