package com.pwojcik.menu;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


public class MenuFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuFactory.class);
    private static final String FILE_PATH = "src/main/java/com/pwojcik/menu/menu.json";

    private List<Menu> menuList;

    public void initializeMenu() {

        menuList = new ArrayList<>();

        if (!Files.exists(Paths.get(FILE_PATH))) {
            createFile();
        } else {
            loadFromJsonFile();
        }

    }

    private void loadFromJsonFile() {

        LOGGER.info("Loading menu from file...");

        try {
            Path menuPath = Paths.get(FILE_PATH);

            if (Files.exists(menuPath)) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                this.menuList = gson.fromJson(Files.readString(menuPath), ArrayList.class);

            } else {
                LOGGER.error("File doesn't exist.");
            }

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
        writeToJsonFile(menu);
    }

    private Menu createMenu(String name, String[] options) {

        LOGGER.info("Creating menu: " + name);

        List<Option> optionList = new ArrayList<>();

        for (int i = 0; i < options.length; i++) {
            optionList.add(new Option(i + 1, options[i]));
        }

        Menu menu = new Menu();
        menu.setId(1);
        menu.setName(name);
        menu.setOptions(optionList);

        return menu;
    }

    private void writeToJsonFile(List<Menu> menuList) {

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

    public List<Menu> getMenuList() {
        return menuList;
    }
}
