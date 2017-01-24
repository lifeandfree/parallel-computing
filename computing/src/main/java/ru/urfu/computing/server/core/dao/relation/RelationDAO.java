/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.relation
 *         Дата создания класса: 13 дек. 2016 г.
 */
package ru.urfu.computing.server.core.dao.relation;

import java.util.Collection;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.relation.Relation;

/**
 * @author lifeandfree
 */
public class RelationDAO extends ElementDAOImpl<Relation> {

    public RelationDAO() {
        super(Relation.class);
    }

    public RelationDAO(Class<Relation> elementClass) {
        super(elementClass);
    }

    public Collection<Relation> getByCameraId(long cameraId, int length) {
        Collection<Relation> relationList = null;
        Session sess = null;
        if (cameraId != 0L) {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                DetachedCriteria criteria = DetachedCriteria.forClass(Relation.class)
                        .add(Restrictions.eq("camera_id", cameraId));
                if (length == 0) {
                    relationList = criteria.getExecutableCriteria(sess).list();
                }
                else {
                    relationList = criteria.getExecutableCriteria(sess).setMaxResults(length).list();
                }
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
            Logfile.getInstance().getLogger().error("The Relation name is null.");
            throw new NullPointerException("The Relation name is null.");
        }
        return relationList;
    }

    public Relation getByPair(long cameraId, long personId) {
        Relation relation = null;
        Session sess = null;
        if (cameraId != 0L && personId != 0L) {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                DetachedCriteria criteria = DetachedCriteria.forClass(Relation.class)
                        .add(Restrictions.eq("camera_id", cameraId)).add(Restrictions.eq("person_id", personId));
                List<Relation> relationList = criteria.getExecutableCriteria(sess).setMaxResults(1).list();
                if (relationList != null && relationList.size() > 0) {
                    relation = relationList.get(0);
                }
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
            Logfile.getInstance().getLogger().error("The Relation name is null.");
            throw new NullPointerException("The Relation name is null.");
        }
        return relation;
    }
}
