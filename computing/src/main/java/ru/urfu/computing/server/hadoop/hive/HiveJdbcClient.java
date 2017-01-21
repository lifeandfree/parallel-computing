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

import ru.urfu.computing.server.core.dao.photo.PhotoDAO;
import ru.urfu.computing.server.core.db.jdbc.ConnectionFactory;
import ru.urfu.computing.server.core.db.jdbc.DbUtil;
import ru.urfu.computing.server.core.db.jdbc.model.database.Database;
import ru.urfu.computing.server.core.db.jdbc.model.photo.FlickrPhoto;
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
    }

    /**
     *
     */
    public void getDataFromHive(String service) {
        String query = "SELECT * FROM json_unhandled where json_unhandled.camera != ''";
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection(this.database.getDbUrl(), this.database.getDbLogin(),
                    this.database.getDbPasswd());
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                FlickrPhoto flickrPhoto = new FlickrPhoto(rs.getString("camera"), rs.getString("id"),
                        rs.getString("latitude"), rs.getString("longitude"), rs.getString("owner"), service);
                PhotoDAO photoDAO = new PhotoDAO();
                photoDAO.writePhotoToBD(flickrPhoto);
                // System.out.println(flickrPhoto.toString());
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
