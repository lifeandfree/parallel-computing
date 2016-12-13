/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.unhandled
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao.unhandled;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;

/**
 * @author lifeandfree
 */
public class UnhandledDAO extends ElementDAOImpl<Unhandled> {

    public UnhandledDAO() {
        super(Unhandled.class);
    }

    public UnhandledDAO(Class<Unhandled> elementClass) {
        super(elementClass);
    }

    public Unhandled addUnhandled(String person, String photo, String tags) {
        Unhandled unhandled = DaoFactory.getInstance().getUnhandledDAO().getByPhoto(photo);
        if (unhandled == null) {
            unhandled = DaoFactory.getInstance().getUnhandledDAO().addElement(new Unhandled(person, photo, tags));
        }
        else {
            Logfile.getInstance().getLogger().error(this.getClass().getName() + " Such photo exists.");
        }
        return unhandled;

    }

    private Unhandled getByPhoto(String photo) {
        Session sess = null;
        Unhandled result = null;
        if (photo.isEmpty() || photo.equals(null)) {
            Logfile.getInstance().getLogger().error(this.getClass().getName() + " Do not set the parameter for search");
        }
        else {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                DetachedCriteria criteria = DetachedCriteria.forClass(Unhandled.class)
                        .add(Restrictions.eq("photo", photo));
                List<Unhandled> contList = criteria.getExecutableCriteria(sess).setMaxResults(1).list();
                if (contList != null && contList.size() > 0) {
                    result = contList.get(0);
                }
            }
            catch (HibernateException e) {
                Logfile.getInstance().getLogger().error(this.getClass().getName() + e.toString());
            }
            catch (Exception e) {
                Logfile.getInstance().getLogger().error(this.getClass().getName() + e.toString());
            }
            finally {
                if (sess != null && sess.isOpen()) {
                    sess.close();
                }
            }
        }
        return result;
    }
}
