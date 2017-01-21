package ru.urfu.computing;

import ru.urfu.computing.server.core.db.jdbc.model.database.DatabaseImpl;
import ru.urfu.computing.server.hadoop.hive.HiveJdbcClient;

public class Application {
    public static void main(String[] args) {

        DatabaseImpl database = new DatabaseImpl("localhost", 10000, "remotetest", "", "", "hive2");
        HiveJdbcClient hiveJdbcClient = new HiveJdbcClient(database);
        hiveJdbcClient.getDataFromHive("flickr.com");

    }

}
