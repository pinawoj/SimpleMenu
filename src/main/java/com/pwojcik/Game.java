package com.pwojcik;

import com.pwojcik.menu.Menu;
import com.pwojcik.menu.MenuFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

class Game {

    private static final Logger LOGGER = LoggerFactory.getLogger(Game.class);

    public void start() {

        MenuFactory menuFactory = new MenuFactory();
        menuFactory.initializeMenu();

        Menu menu = menuFactory.getMenuList().get(0);

        boolean running = true;
        while (running) {

            menu.display();

            // TODO Change switch for something better (this solution is temporary)
            switch (getOption(menu)) {
                case 1:
                    System.out.println(menu.getOptions().get(0).getDescription());
                    System.out.println("Starting game... Please prepare...");
                    break;
                case 2:
                    System.out.println(menu.getOptions().get(1).getDescription());
                    menu = menuFactory.getMenuList().get(1);
                    menu.display();
                    while (running) {
                        switch (getOption(menu)) {
                            case 1:
                                System.out.println(menu.getOptions().get(0).getDescription());
                                break;
                            case 2:
                                System.out.println(menu.getOptions().get(1).getDescription());
                                break;
                            case 3:
                                System.out.println(menu.getOptions().get(2).getDescription());
                                menu = menuFactory.getMenuList().get(0);
                                running = false;
                                break;
                            default:
                                break;
                        }
                    }
                    running = true;
                    break;
                case 3:
                    System.out.println(menu.getOptions().get(2).getDescription());
                    System.out.println("Java Developer: Paulina Wojcik");
                    break;
                case 4:
                    System.out.println(menu.getOptions().get(3).getDescription());
                    running = false;
                    break;
                default:
                    break;
            }
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
