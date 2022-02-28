package com.pwojcik.menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;


public class MenuOperator {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuOperator.class);
    private static final String FILE_PATH = "src/main/java/com/pwojcik/menu/menu.json";

    private List<Menu> menuList;

    public MenuOperator() {

        menuList = new ArrayList<>();

        if (!Files.exists(Paths.get(FILE_PATH))) {
            createFile();
        } else {

            loadFromFile();
            for (Menu menu : this.menuList) {
                loadActions(menu.getName(), menu.getOptions());
            }

        }

    }

    private void loadFromFile() {

        LOGGER.info("Loading menu from file...");

        try {
            Path menuPath = Paths.get(FILE_PATH);

            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            Type listOfMenuObjects = new TypeToken<ArrayList<Menu>>() {}.getType();
            this.menuList = gson.fromJson(Files.readString(menuPath), listOfMenuObjects);

        } catch (IOException e) {
            LOGGER.error("Couldn't load menu data from file.");
        }
    }

    private void createFile() {

        LOGGER.info("Creating new file...");

        List<Menu> menu = new ArrayList<>();
        menu.add(createMenu("Main", new String[]{"Start Game", "Options", "Credits", "Exit"}));
        menu.add(createMenu("Options", new String[]{"Graphic", "Sound", "Back"}));
        this.menuList = menu;
        writeToFile(menu);
    }

    private void writeToFile(List<Menu> menuList) {

        LOGGER.info("Writing menu list to file...");

        Path menuPath = Paths.get(FILE_PATH);

        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();

            String jsonMenu = gson.toJson(menuList);
            Files.write(menuPath, jsonMenu.getBytes());

        } catch (IOException e) {
            LOGGER.error("Couldn't write menu data to file.");
        }
    }

    private Menu createMenu(String name, String[] options) {

        List<Option> optionList = new ArrayList<>();

        List<Function<String, Menu>> actions = createActions(name);

        for (int i = 0; i < options.length; i++) {
            optionList.add(new Option(i + 1, options[i], actions.get(i)));
        }

        return new Menu(name, optionList);
    }

    private void loadActions(String name, List<Option> options) {

        List<Function<String, Menu>> actions = createActions(name);

        for (int i = 0; i < options.size(); i++) {
            options.get(i).setAction(actions.get(i));
        }

    }

    public List<Function<String, Menu>> createActions(String name) {

        List<Function<String, Menu>> actionList = new ArrayList<>();

        if (name.equals("Main")) {

            actionList.add(n -> {
                System.out.println("Chosen option: " + n + "\nPrepare for new game...");
                return getMenuList().get(0);
            });
            actionList.add(n -> {
                System.out.println("Chosen option: " + n);
                return getMenuList().get(1);
            });
            actionList.add(n -> {
                System.out.println("Chosen option: " + n + "\nDevelopers: Paulina Woj");
                return getMenuList().get(0);
            });
            actionList.add(n -> {
                System.out.println("Chosen option: " + n);
                return null;
            });

        } else if (name.equals("Options")) {

            actionList.add(n -> {
                System.out.println("Chosen option: " + n + "\nGraphic: ...");
                return getMenuList().get(1);
            });
            actionList.add(n -> {
                System.out.println("Chosen option: " + n + "\nSound: ...");
                return getMenuList().get(1);
            });
            actionList.add(n -> {
                System.out.println("Chosen option: " + n);
                return getMenuList().get(0);
            });

        } else {
            LOGGER.warn("Unknown menu name - couldn't load actions.");
        }

        return actionList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }
}
