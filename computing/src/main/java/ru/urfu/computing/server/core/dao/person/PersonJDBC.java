/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.person
 *         Дата создания класса: 24 янв. 2017 г.
 */
package ru.urfu.computing.server.core.dao.person;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ru.urfu.computing.server.core.db.jdbc.ConnectionFactory;
import ru.urfu.computing.server.core.db.jdbc.DbUtil;
import ru.urfu.computing.server.core.db.jdbc.model.database.Database;
import ru.urfu.computing.server.core.db.jdbc.model.database.DatabaseImpl;
import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class PersonJDBC {
    public static void main(String[] args) {
        PersonJDBC personJDBC = new PersonJDBC(
                new DatabaseImpl("localhost", 5432, "computing3", "computing", "123", "postgresql"));
        personJDBC.getPersonByGeoJDBC(4, -180, -180, 180, 180);
    }

    private Connection connection;
    private Database database;
    private Statement statement;

    /**
     *
     */
    public PersonJDBC(Database database) {
        this.database = database;
    }

    public ArrayList<String> getPersonByGeoJDBC(long cameraId, double latitudeX, double longitudeX, double latitudeY,
            double longitudeY) {
        ArrayList<String> personNames = new ArrayList<>();
        if (latitudeX > latitudeY) {
            double tmplat = latitudeY;
            latitudeY = latitudeX;
            latitudeX = tmplat;
        }
        if (longitudeX > longitudeY) {
            double tmplat = longitudeY;
            longitudeY = longitudeX;
            longitudeX = tmplat;
        }
        String query = "SELECT person.name FROM (SELECT person_id FROM location where (latitude BETWEEN " + latitudeX
                + " and " + latitudeY + ") and (longitude BETWEEN " + longitudeX + " and " + longitudeY
                + ")) as locper INNER JOIN person ON (locper.person_id=person.id) AND person.id IN (SELECT person_id FROM relation where camera_id="
                + cameraId + ");";
        ResultSet rs = null;
        try {
            connection = ConnectionFactory.getConnection(this.database.getDbUrl(), this.database.getDbLogin(),
                    this.database.getDbPasswd());
            statement = connection.createStatement();
            rs = statement.executeQuery(query);
            while (rs.next()) {
                personNames.add((rs.getString("name")));
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
        return personNames;
    }

}
