/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.jdbc
 *         Дата создания класса: 18 янв. 2017 г.
 */
package ru.urfu.computing.server.core.db.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;

import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class DbUtil {

    /**
     * Проверяет имеется исключение типа WARNING.
     *
     * @param warn
     *            - исключение.
     */
    public static void checkWarnings(SQLWarning warn) {
        if (warn != null) {
            while (warn != null) {
                Logfile.getInstance().getLogger()
                        .warn("Unable to Connect to Database." + " SQLState: " + warn.getSQLState() + " Message: "
                                + warn.getMessage() + " Vendor code: " + warn.getErrorCode(), warn);
                warn.getNextException();
            }
        }
    }

    /**
     * Закрывает соединение с БД.
     *
     * @param connection
     *            - соединение с БД.
     */
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
                Logfile.getInstance().getLogger().info("Connection closed");
            }
            catch (SQLException e) {
                Logfile.getInstance().getLogger().error("Unable to close connection." + " SQLState: " + e.getSQLState()
                        + " Message: " + e.getMessage() + " Vendor code: " + e.getErrorCode(), e);
                e.getNextException();
            }
            catch (Exception e) {
                Logfile.getInstance().getLogger().error("Unable to close connection" + " Message: " + e.getMessage(),
                        e);
            }
        }
    }

    /**
     * Закрывает обработку результатов с запроса с БД.
     *
     * @param resultSet
     */
    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
                Logfile.getInstance().getLogger().info("resultSet completed");
            }
            catch (SQLException e) {
                Logfile.getInstance().getLogger().error("Unable to close resultSet." + " SQLState: " + e.getSQLState()
                        + " Message: " + e.getMessage() + " Vendor code: " + e.getErrorCode(), e);
                e.getNextException();
            }
            catch (Exception e) {
                Logfile.getInstance().getLogger().error("Unable to close resultSet" + " Message: " + e.getMessage(), e);
            }
        }
    }

    /**
     * Закрывает statement.
     *
     * @param statement
     */
    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
                Logfile.getInstance().getLogger().info("Request completed");
            }
            catch (SQLException e) {
                Logfile.getInstance().getLogger().error("Unable to close statement." + " SQLState: " + e.getSQLState()
                        + " Message: " + e.getMessage() + " Vendor code: " + e.getErrorCode(), e);
                e.getNextException();
            }
            catch (Exception e) {
                Logfile.getInstance().getLogger().error("Unable to close statement" + " Message: " + e.getMessage(), e);
            }
        }
    }

    /**
     *
     */
    public DbUtil() {
        // TODO Auto-generated constructor stub
    }

}
