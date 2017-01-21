package ru.urfu.computing;

import ru.urfu.computing.server.core.dao.DaoFactory;

public class Application {
    public static void main(String[] args) {

        long hh = DaoFactory.getInstance().getUnhandledDAO().getSizeOfTable();
        System.out.println(hh);
        // HiveJdbcClient hiveJdbcClient = new HiveJdbcClient();
        // hiveJdbcClient.getClass();
        // System.out.println("Hello World!");
        // Logfile.getInstance().getLogger().info(Application.class.getName() + ": Hello World!");
        // Unhandled unhandled = DaoFactory.getInstance().getUnhandledDAO().addUnhandled("photo2", "tags3");
        // System.out.println(unhandled);

        // Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        // System.out.println(hh);
        // long min_upload_date = 1482519900;
        // long max_upload_date = 1482521700;
        // long interval = 600;
        // Flickr flickr = new Flickr();
        // flickr.getAndWriteUnhanledToDB(min_upload_date, max_upload_date, interval);
        // Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        // System.out.println(hh);
        // System.out.println(DaoFactory.getInstance().getUnhandledDAO().getElementByID(2L).toString());

    }

}
