/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.camera
 *         Дата создания класса: 25 дек. 2016 г.
 */
package ru.urfu.computing.server.core.dao.camera;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.camera.Camera;
import ru.urfu.computing.server.utils.FileHandler;

/**
 * @author lifeandfree
 */
public class CameraDAO extends ElementDAOImpl<Camera> {

    public CameraDAO() {
        super(Camera.class);
    }

    /**
     * @param elementClass
     */
    public CameraDAO(Class<Camera> elementClass) {
        super(elementClass);
    }

    public Camera getByName(String name) {
        String method = new FileHandler().getMethodName() + "(" + (name != null ? name : " null") + "): ";
        Camera camera = null;
        Session sess = null;
        if (name != null) {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                DetachedCriteria criteria = DetachedCriteria.forClass(Camera.class).add(Restrictions.eq("name", name));
                List<Camera> cameraList = criteria.getExecutableCriteria(sess).setMaxResults(1).list();
                if (cameraList != null && cameraList.size() > 0) {
                    camera = cameraList.get(0);
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
            Logfile.getInstance().getLogger().error(method + "The camera name is null.");
            throw new NullPointerException("The camera name is null.");
        }
        return camera;
    }

}
