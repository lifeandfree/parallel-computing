/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.model.person
 *         Дата создания класса: 23 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.model.person;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

/**
 * @author lifeandfree
 */
@Entity
@Table(name = "UNHANDLED")
public class Person implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2225628683943215488L;

    @Column(name = "city")
    @Type(type = "text")
    private String city;

    @Column(name = "device", nullable = false)
    @Type(type = "text")
    private String device;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "man", nullable = false)
    @Type(type = "text")
    private String man;

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the device
     */
    public String getDevice() {
        return device;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the man
     */
    public String getMan() {
        return man;
    }

    /**
     * @param city
     *            the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @param device
     *            the device to set
     */
    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * @param man
     *            the man to set
     */
    public void setMan(String man) {
        this.man = man;
    }
}
