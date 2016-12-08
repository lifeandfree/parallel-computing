package ru.urfu.computing;

import java.util.Collection;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;

public class Application {
    public static void main(String[] args) {

        System.out.println("Hello World!");
        Logfile.getInstance().getLogger().info(Application.class.getName() + ": Hello World!");
        Unhandled unhandled = new Unhandled("man4", "photo4", "tags4");
        DaoFactory.getInstance().getUnhandledDAO().addElement(unhandled);
        Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        System.out.println(hh);
        System.out.println(DaoFactory.getInstance().getUnhandledDAO().getElementByID(2L).toString());

    }

}
