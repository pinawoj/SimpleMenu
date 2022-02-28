package com.pwojcik.menu;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

public class Option {

    private static final Logger LOGGER = LoggerFactory.getLogger(Option.class);

    private Integer id;
    private String description;
    private transient Function<String, Menu> action;


    public Option(Integer id, String description, Function<String, Menu> action) {

        LOGGER.info("Creating option: {}", description);

        this.id = id;
        this.description = description;
        this.action = action;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Function<String, Menu> getAction() {
        return action;
    }

    public void setAction(Function<String, Menu> action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", action=" + action +
                '}';
    }

}
