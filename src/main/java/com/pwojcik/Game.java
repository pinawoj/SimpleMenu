package com.pwojcik;

import com.pwojcik.menu.MainMenu;
import com.pwojcik.menu.Menu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class Game {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    public void start() {
        Menu menu = new MainMenu();
        while (menu != null) {
            menu.display();
            menu = menu.runOption(getOptionId(menu.getOptions().size()));
        }
    }

    private int getOptionId(int menuSize) {
        int option;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {
                option = scanner.nextInt();
                if (option >= 1 && option <= menuSize) {
                    break;
                } else {
                    LOGGER.warn("Argument out of range.");
                    System.out.println("Chosen option is out of range.");
                }
            } catch (IllegalArgumentException e) {
                LOGGER.warn("Illegal argument.");
                System.out.println("Incorrect option.");
            }
        }
        return option - 1;
    }

}
