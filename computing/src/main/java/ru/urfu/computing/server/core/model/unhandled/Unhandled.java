/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.mode.unhandled
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.model.unhandled;

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
public class Unhandled implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7492189905167007468L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "photo", nullable = false)
    private String photo;

    @Column(name = "tags")
    @Type(type = "text")
    private String tags;

    /**
     *
     */
    public Unhandled() {
        super();
        this.photo = null;
        this.tags = null;
    }

    public Unhandled(String photo, String tags) {
        super();
        this.photo = photo;
        this.tags = tags;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @return the photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * @return the tags
     */
    public String getTags() {
        return tags;
    }

    /**
     * @param photo
     *            the photo to set
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * @param tags
     *            the tags to set
     */
    public void setTags(String tags) {
        this.tags = tags;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "Unhandled [id=" + id + ", photo=" + photo + ", tags=" + tags + "]";
    }

}
