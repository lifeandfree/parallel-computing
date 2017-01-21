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

    @Column(name = "name", nullable = false)
    @Type(type = "text")
    private String name;

    @Column(name = "service_id", nullable = false)
    private long service_id;

    /**
     * @param name
     * @param service_id
     */
    public Person() {
        super();
        this.name = null;
        this.service_id = 0L;
    }

    /**
     * @param name
     * @param service_id
     */
    public Person(String name, long service_id) {
        super();
        this.name = name;
        this.service_id = service_id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the service_id
     */
    public long getService_id() {
        return service_id;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param service_id
     *            the service_id to set
     */
    public void setService_id(long service_id) {
        this.service_id = service_id;
    }

}
