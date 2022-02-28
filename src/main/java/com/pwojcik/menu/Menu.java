package com.pwojcik.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


public class Menu {

    private static final Logger LOGGER = LoggerFactory.getLogger(Menu.class);

    private String name;
    private List<Option> options;

    public Menu(String name, List<Option> options) {

        LOGGER.info("Creating menu: {}", name);

        this.name = name;
        this.options = options;
    }

    public void display() {

        System.out.println("[  " + this.name.toUpperCase() + "  ]");
        System.out.println("===============");

        for (Option option : this.options) {
            System.out.println(option.getId() + ". " + option.getDescription());
        }

        System.out.println("\n Choose option: ");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}
