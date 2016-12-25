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
@Table(name = "PERSON")
public class Person implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2225628683943215488L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "service", nullable = false)
    @Type(type = "text")
    private String service;

    @Column(name = "service_id", nullable = false)
    @Type(type = "text")
    private String service_id;

    /**
     * @param service
     * @param service_id
     */
    public Person() {
        super();
        this.service = null;
        this.service_id = null;
    }

    /**
     * @param service
     * @param service_id
     */
    public Person(String service, String service_id) {
        super();
        this.service = service;
        this.service_id = service_id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the service
     */
    public String getService() {
        return service;
    }

    /**
     * @return the service_id
     */
    public String getService_id() {
        return service_id;
    }

    /**
     * @param service
     *            the service to set
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * @param service_id
     *            the service_id to set
     */
    public void setService_id(String service_id) {
        this.service_id = service_id;
    }
}
