/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.jdbc.model.photo
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing.server.core.db.jdbc.model.photo;

/**
 * @author lifeandfree
 */
public interface Photo {

    public String getCamera();

    public String getId();

    public String getLatitude();

    public String getLongitude();

    public String getOwner();

    public String getService();

    public void setCamera(String camera);

    public void setId(String id);

    public void setLatitude(String latitude);

    public void setLongitude(String longitude);

    public void setOwner(String owner);

    public void setService(String service);

}
