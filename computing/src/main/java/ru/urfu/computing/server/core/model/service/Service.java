/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.model.social
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing.server.core.model.service;

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
@Table(name = "SERVICE")
public class Service implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 6239229087830818018L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    @Type(type = "text")
    private String name;

    /**
     *
     */
    public Service() {
        super();
        this.name = null;
    }

    /**
     * @param name
     */
    public Service(String name) {
        super();
        this.name = name;
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
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
