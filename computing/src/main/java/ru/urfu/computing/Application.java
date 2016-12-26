package ru.urfu.computing;

import java.util.Collection;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;
import ru.urfu.computing.server.download.flickr.Flickr;

public class Application {
    public static void main(String[] args) {

        // System.out.println("Hello World!");
        // Logfile.getInstance().getLogger().info(Application.class.getName() + ": Hello World!");
        // Unhandled unhandled = DaoFactory.getInstance().getUnhandledDAO().addUnhandled("photo2", "tags3");
        // System.out.println(unhandled);

        // Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        // System.out.println(hh);
        long min_upload_date = 1482519900;
        long max_upload_date = 1482521700;
        long interval = 600;
        Flickr.getAndWriteUnhanledToDB(min_upload_date, max_upload_date, interval);
        Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        System.out.println(hh);
        // System.out.println(DaoFactory.getInstance().getUnhandledDAO().getElementByID(2L).toString());

    }

}
