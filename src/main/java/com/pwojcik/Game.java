package com.pwojcik;

import com.pwojcik.menu.Menu;
import com.pwojcik.menu.MenuOperator;

import com.pwojcik.menu.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class Game {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    public void start() {

        MenuOperator menuOperator = new MenuOperator();

        Menu menu = menuOperator.getMenuList().get(0);

        while (menu != null) {

            menu.display();

            int optionId = getOption(menu) - 1;
            Option option = menu.getOptions().get(optionId);
            menu = option.getAction().apply(option.getDescription());

        }

    }

    private int getOption(Menu menu) {

        int option;

        while (true) {
            Scanner scanner = new Scanner(System.in);
            try {

                option = scanner.nextInt();
                if (option >= 1 && option <= menu.getOptions().size()) {
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

        return option;
    }

}
