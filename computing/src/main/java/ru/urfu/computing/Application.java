package ru.urfu.computing;

import ru.urfu.computing.core.logger.Logger;

public class Application {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        Logger.getInstance().getLogger().info(Application.class.getName() + ": Hello World!");
    }

}
