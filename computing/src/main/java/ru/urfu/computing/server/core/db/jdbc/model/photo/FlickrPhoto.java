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
public class FlickrPhoto implements Photo {

    private String camera;
    private String id;
    private String latitude;
    private String longitude;
    private String owner;
    private String service;

    /**
     *
     */
    public FlickrPhoto() {
        this.camera = null;
        this.id = null;
        this.latitude = null;
        this.longitude = null;
        this.owner = null;
    }

    /**
     * @param camera
     * @param id
     * @param latitude
     * @param longitude
     * @param owner
     */
    public FlickrPhoto(String camera, String id, String latitude, String longitude, String owner, String service) {
        super();
        this.camera = camera;
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.owner = owner;
        this.service = service;
    }

    /**
     * @return the camera
     */
    @Override
    public String getCamera() {
        return camera;
    }

    /**
     * @return the id
     */
    @Override
    public String getId() {
        return id;
    }

    /**
     * @return the latitude
     */
    @Override
    public String getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    @Override
    public String getLongitude() {
        return longitude;
    }

    /**
     * @return the owner
     */
    @Override
    public String getOwner() {
        return owner;
    }

    @Override
    public String getService() {
        return service;
    }

    /**
     * @param camera
     *            the camera to set
     */
    @Override
    public void setCamera(String camera) {
        this.camera = camera;
    }

    /**
     * @param id
     *            the id to set
     */
    @Override
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    @Override
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @param owner
     *            the owner to set
     */
    @Override
    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Override
    public void setService(String service) {
        this.service = service;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FlickrPhoto [camera=" + camera + ", id=" + id + ", latitude=" + latitude + ", longitude=" + longitude
                + ", owner=" + owner + ", service=" + service + "]";
    }

}
