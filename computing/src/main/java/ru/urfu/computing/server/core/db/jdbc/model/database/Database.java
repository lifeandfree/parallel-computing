/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.jdbc.model
 *         Дата создания класса: 18 янв. 2017 г.
 */
package ru.urfu.computing.server.core.db.jdbc.model.database;

/**
 * @author lifeandfree
 */
public interface Database {

    public String getDbLogin();

    public String getDbName();

    public String getDbPasswd();

    public String getDbServer();

    public String getDbUrl();

    public int getPort();

    public void setDbLogin(String dbLogin);

    public void setDbName(String dbName);

    public void setDbPasswd(String dbPasswd);

    public void setDbServer(String dbServer);

    public void setDbUrl(String dbUrl);

    public void setPort(int port);

}
