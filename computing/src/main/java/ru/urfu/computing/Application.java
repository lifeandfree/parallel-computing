package ru.urfu.computing;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;

public class Application {
    public static void main(String[] args) {

        System.out.println("Hello World!");

        Logfile.getInstance().getLogger().info(Application.class.getName() + ": Hello World!");
        Unhandled unhandled = new Unhandled("man", "photo", "tags");
        DaoFactory.getInstance().getUnhandledDAO().addElement(unhandled);
        System.out.println(DaoFactory.getInstance().getUnhandledDAO().getElementByID(1L).toString());
    }

}
