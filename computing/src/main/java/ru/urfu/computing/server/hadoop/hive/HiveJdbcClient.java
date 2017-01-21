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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ru.urfu.computing.server.core.db.jdbc.ConnectionFactory;
import ru.urfu.computing.server.core.db.jdbc.DbUtil;
import ru.urfu.computing.server.core.db.jdbc.model.database.Database;
import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class HiveJdbcClient {

    // private static String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver";
    // private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    /* Соединение с БД */
    private Connection connection;
    private Database database;
    private Statement statement;

    /**
     *
     */
    public HiveJdbcClient(Database database) {
        this.database = database;
        // try {
        // Class.forName(driverName);
        // }
        // catch (ClassNotFoundException e) {
        // Logfile.getInstance().getLogger().error(e);
        // System.exit(1);
        // }
        // Connection con;
        // Statement stmt;
        // try {
        // con = DriverManager.getConnection("jdbc:hive2://localhost:10000/remotetest", "", "");
        // stmt = con.createStatement();
        // ResultSet res = stmt.executeQuery("SELECT * FROM unhandled");
        // while (res.next()) {
        // System.out.println(res.getString(1) + " " + res.getString(2) + " " + res.getString(3));
        // }
        // }
        // catch (SQLException e) {
        // Logfile.getInstance().getLogger().error(e);
        // }

    }

    /**
     *
     */
    public void getDataFromHive() {
        String query = "SELECT * FROM json_unhandled where json_unhandled.camera != ''";
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection(this.database.getDbUrl(), this.database.getDbLogin(),
                    this.database.getDbPasswd());
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | "
                        + rs.getString(4) + " | " + rs.getString(5));
            }
        }
        catch (SQLException e) {
            Logfile.getInstance().getLogger().error("Can not get the point. " + " SQLState: " + e.getSQLState()
                    + " Message: " + e.getMessage() + " Vendor code: " + e.getErrorCode(), e);
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger().error("Can not get the point. Message: " + e.getMessage(), e);
        }
        finally {
            DbUtil.close(rs);
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
    }

}
