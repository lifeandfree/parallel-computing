/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.hadoop.hive
 *         Дата создания класса: 10 янв. 2017 г.
 */
package ru.urfu.computing.server.hadoop.hive;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class HiveJdbcClient {

    // private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    /**
     *
     */
    public HiveJdbcClient() {
        try {
            Class.forName(driverName);
        }
        catch (ClassNotFoundException e) {
            Logfile.getInstance().getLogger().error(e);
            System.exit(1);
        }
        Connection con;
        Statement stmt;
        try {
            con = DriverManager.getConnection("jdbc:hive2://localhost:10000/remotetest", "", "");
            stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM unhandled");
            while (res.next()) {
                System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3));
            }
        }
        catch (SQLException e) {
            Logfile.getInstance().getLogger().error(e);
        }

    }

}
