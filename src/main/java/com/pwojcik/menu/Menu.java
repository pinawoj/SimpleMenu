package com.pwojcik.menu;

import java.util.List;


public class Menu {

    private Integer id;
    private String name;
    private List<Option> options;


    public void display() {

        System.out.println("[  " + this.name.toUpperCase() + "  ]");
        System.out.println("===============");

        for (Option option : this.options) {
            System.out.println(option.getId() + ". " + option.getDescription());
        }

        System.out.println("\n Choose option: ");
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}
