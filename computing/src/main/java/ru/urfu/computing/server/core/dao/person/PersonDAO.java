/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao
 *         Дата создания класса: 23 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao.person;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.person.Person;
import ru.urfu.computing.server.utils.FileHandler;

/**
 * @author lifeandfree
 */
public class PersonDAO extends ElementDAOImpl<Person> {

    public static void main(String[] args) {

        // getPersonByGeoJDBC(4, -180, 180, -180, 180);
    }

    public PersonDAO() {
        super(Person.class);
    }

    public PersonDAO(Class<Person> elementClass) {
        super(elementClass);
    }

    public Person getByName(String name, long serviceId) {
        String method = new FileHandler().getMethodName() + "(" + (name != null ? name : " null") + "): ";
        Person person = null;
        Session sess = null;
        if (name != null) {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                DetachedCriteria criteria = DetachedCriteria.forClass(Person.class).add(Restrictions.eq("name", name))
                        .add(Restrictions.eq("service_id", serviceId));
                List<Person> personList = criteria.getExecutableCriteria(sess).setMaxResults(1).list();
                if (personList != null && personList.size() > 0) {
                    person = ((personList.get(0)));
                }
            }
            catch (HibernateException e) {
                Logfile.getInstance().getLogger().error(method + e.toString());
            }
            catch (Exception e) {
                Logfile.getInstance().getLogger().error(method + e.toString());
            }
            finally {
                if (sess != null && sess.isOpen()) {
                    sess.close();
                }
            }
        }
        else {
            Logfile.getInstance().getLogger().error(method + "The person name is null.");
            throw new NullPointerException("The person name is null.");
        }
        return person;
    }

    // TODO не рабочий
    public List getPersonByGeo(long cameraId, double latitudeX, double longitudeX, double latitudeY,
            double longitudeY) {
        if (latitudeX > latitudeY) {
            double tmplat = latitudeY;
            latitudeY = latitudeX;
            latitudeX = tmplat;
        }
        if (longitudeX > longitudeY) {
            double tmplat = longitudeY;
            longitudeY = longitudeX;
            longitudeX = tmplat;
        }
        List list = null;
        // List<Person> person = null;
        Session sess = null;
        if (cameraId != 0L) {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                Query query = sess.createQuery(
                        "SELECT person.name FROM (SELECT person_id FROM location where (latitude BETWEEN " + latitudeX
                                + " and " + latitudeY + ") and (longitude BETWEEN " + longitudeX + " and " + longitudeY
                                + ")) as locper INNER JOIN person ON (locper.person_id=person.id) AND person.id IN (SELECT person_id FROM relation where camera_id="
                                + cameraId + ");");
                list = query.getResultList();
                // person = query.list();

                // List<Person> personList = criteria.getExecutableCriteria(sess).setMaxResults(1).list();
                // if (personList != null && personList.size() > 0) {
                // person = ((personList.get(0)));
                // }
            }
            catch (HibernateException e) {
                Logfile.getInstance().getLogger().error(e.toString());
            }
            catch (Exception e) {
                Logfile.getInstance().getLogger().error(e.toString());
            }
            finally {
                if (sess != null && sess.isOpen()) {
                    sess.close();
                }
            }
        }
        else {
            Logfile.getInstance().getLogger().error("The person name is null.");
            throw new NullPointerException("The person name is null.");
        }
        return list;
    }

}
