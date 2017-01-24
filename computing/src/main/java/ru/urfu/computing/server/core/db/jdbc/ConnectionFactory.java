/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db
 *         Дата создания класса: 18 янв. 2017 г.
 */
package ru.urfu.computing.server.core.db.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 *         Фабрика по созданию соединений для подключения к БД.
 */
public class ConnectionFactory {
    public static String DRIVER_CLASS_HIVE = "org.apache.hive.jdbc.HiveDriver";
    public static final String DRIVER_CLASS_MYSQL = "com.mysql.jdbc.Driver";
    public static String DRIVER_CLASS_POSTGRESQL = "org.postgresql.Driver";

    private static ConnectionFactory instance = new ConnectionFactory(DRIVER_CLASS_POSTGRESQL);

    public static Connection getConnection(String url, String user, String password) {
        return instance.createConnection(url, user, password);
    }

    private ConnectionFactory(String driverClass) {
        try {
            Class.forName(driverClass);
        }
        catch (ClassNotFoundException e) {
            Logfile.getInstance().getLogger().error("Unable to load " + driverClass, e);
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger().error("Unable to load " + driverClass + " Message: " + e.getMessage(), e);
        }
    }

    private Connection createConnection(String url, String user, String password) {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            DbUtil.checkWarnings(connection.getWarnings());
            Logfile.getInstance().getLogger().info("Connect to Database.");
        }
        catch (SQLException e) {
            Logfile.getInstance().getLogger().error("Unable to Connect to Database." + " SQLState: " + e.getSQLState()
                    + " Message: " + e.getMessage() + " Vendor code: " + e.getErrorCode(), e);
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger().error("Unable to Connect to Database." + " Message: " + e.getMessage(),
                    e);
        }
        return connection;
    }

}
