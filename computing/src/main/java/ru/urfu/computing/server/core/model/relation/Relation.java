/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.model.relation
 *         Дата создания класса: 13 дек. 2016 г.
 */
package ru.urfu.computing.server.core.model.relation;

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
@Table(name = "RELATION")
public class Relation implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 9004862301194022571L;

    @Column(name = "camera_id", nullable = true)
    private Long camera_id;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "person_id", nullable = false)
    private Long person_id;

    public Relation() {
        super();
        this.camera_id = null;
        this.person_id = null;
    }

    /**
     * @param camera_id
     * @param person_id
     */
    public Relation(Long camera_id, Long person_id) {
        super();
        this.camera_id = camera_id;
        this.person_id = person_id;
    }

    /**
     * @return the camera_id
     */
    public Long getCamera_id() {
        return camera_id;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the person_id
     */
    public Long getPerson_id() {
        return person_id;
    }

    /**
     * @param camera_id
     *            the camera_id to set
     */
    public void setCamera_id(Long camera_id) {
        this.camera_id = camera_id;
    }

    /**
     * @param person_id
     *            the person_id to set
     */
    public void setPerson_id(Long person_id) {
        this.person_id = person_id;
    }

}
