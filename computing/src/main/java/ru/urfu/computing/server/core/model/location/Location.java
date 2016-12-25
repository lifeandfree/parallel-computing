/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.model.location
 *         Дата создания класса: 25 дек. 2016 г.
 */
package ru.urfu.computing.server.core.model.location;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author lifeandfree
 */
@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8392713790293767648L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "person_id", nullable = false)
    private Long person_id;

    public Location() {
        super();
        this.latitude = null;
        this.longitude = null;
        this.person_id = 0L;
    }

    /**
     * @param latitude
     * @param longitude
     * @param person_id
     */
    public Location(Double latitude, Double longitude, Long person_id) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.person_id = person_id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     * @return the person_id
     */
    public Long getPerson_id() {
        return person_id;
    }

    /**
     * @param latitude
     *            the latitude to set
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     * @param longitude
     *            the longitude to set
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     * @param person_id
     *            the person_id to set
     */
    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

}
