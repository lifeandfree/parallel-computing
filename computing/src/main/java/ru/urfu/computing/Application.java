package ru.urfu.computing;

import ru.urfu.computing.server.core.db.jdbc.model.database.DatabaseImpl;
import ru.urfu.computing.server.hadoop.hive.HiveJdbcClient;

public class Application {
    public static void main(String[] args) {

        // long hh = DaoFactory.getInstance().getUnhandledDAO().getSizeOfTable();
        // System.out.println(hh);
        DatabaseImpl database = new DatabaseImpl("localhost", 10000, "remotetest", "", "", "hive2");
        HiveJdbcClient hiveJdbcClient = new HiveJdbcClient(database);
        hiveJdbcClient.getDataFromHive();
        // System.out.println("Hello World!");
        // Logfile.getInstance().getLogger().info(Application.class.getName() + ": Hello World!");
        // Unhandled unhandled = DaoFactory.getInstance().getUnhandledDAO().addUnhandled("photo2", "tags3");
        // System.out.println(unhandled);

        // Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        // System.out.println(hh);
        // long min_upload_date = 1482519900;
        // long max_upload_date = 1483819900;
        // long interval = 600;
        // Flickr flickr = new Flickr();
        // flickr.getAndWriteUnhanledToDB(min_upload_date, max_upload_date, interval);
        // Collection<Unhandled> hh = DaoFactory.getInstance().getUnhandledDAO().getAllElements();
        // System.out.println(hh);
        // System.out.println(DaoFactory.getInstance().getUnhandledDAO().getElementByID(2L).toString());

    }

}
