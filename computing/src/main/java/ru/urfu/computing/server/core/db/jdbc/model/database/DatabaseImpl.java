/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.jdbc.model
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing.server.core.db.jdbc.model.database;

/**
 * @author lifeandfree
 */
public class DatabaseImpl implements Database {

    private String dbLogin;
    private String dbName;
    private String dbPasswd;
    private String dbServer;
    private String dbType;
    private String dbUrl;
    private int port;

    public DatabaseImpl() {
        this.dbLogin = "";
        this.dbName = "";
        this.dbPasswd = "";
        this.dbServer = "";
        this.dbUrl = "";
        this.dbType = "";
        this.port = 0;
    }

    public DatabaseImpl(String dbServer, int port, String dbName, String dbLogin, String dbPasswd, String dbType) {
        this.dbLogin = dbLogin;
        this.dbName = dbName;
        this.port = port;
        this.dbPasswd = dbPasswd;
        this.dbServer = dbServer;
        this.dbType = dbType;
        this.dbUrl = "jdbc:" + this.dbType + "://" + this.dbServer + ":" + this.port + "/" + this.dbName;
    }

    public DatabaseImpl(String dbServer, String dbName, String dbLogin, String dbPasswd, String dbType) {
        this.dbLogin = dbLogin;
        this.dbName = dbName;
        this.dbPasswd = dbPasswd;
        this.dbServer = dbServer;
        this.dbType = dbType;
        this.dbUrl = "jdbc:" + this.dbType + "://" + dbServer + "/" + dbName;
    }

    public DatabaseImpl(String dbServer, String dbName, String dbLogin, String dbPasswd, String dbUrl, String dbType) {
        this.dbLogin = dbLogin;
        this.dbName = dbName;
        this.dbPasswd = dbPasswd;
        this.dbServer = dbServer;
        this.dbType = dbType;
        this.dbUrl = dbUrl;
    }

    @Override
    public String getDbLogin() {
        return this.dbLogin;
    }

    @Override
    public String getDbName() {
        return this.dbName;
    }

    @Override
    public String getDbPasswd() {
        return this.dbPasswd;
    }

    @Override
    public String getDbServer() {
        return this.dbServer;
    }

    /**
     * @return the dbType
     */
    public String getDbType() {
        return dbType;
    }

    @Override
    public String getDbUrl() {
        return this.dbUrl;
    }

    @Override
    public int getPort() {
        return this.port;
    }

    @Override
    public void setDbLogin(String dbLogin) {
        this.dbLogin = dbLogin;
    }

    @Override
    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    @Override
    public void setDbPasswd(String dbPasswd) {
        this.dbPasswd = dbPasswd;
    }

    @Override
    public void setDbServer(String dbServer) {
        this.dbServer = dbServer;
    }

    /**
     * @param dbType
     *            the dbType to set
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    @Override
    public void setDbUrl(String dbUrl) {
        this.dbUrl = dbUrl;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }

}
