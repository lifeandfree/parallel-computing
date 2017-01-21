/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.service
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing.server.core.dao.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.service.Service;
import ru.urfu.computing.server.utils.FileHandler;

/**
 * @author lifeandfree
 */
public class ServiceDAO extends ElementDAOImpl<Service> {

    public ServiceDAO() {
        super(Service.class);
    }

    /**
     * @param elementClass
     */
    public ServiceDAO(Class<Service> elementClass) {
        super(elementClass);
    }

    public Service getByName(String name) {
        String method = new FileHandler().getMethodName() + "(" + (name != null ? name : " null") + "): ";
        Service service = null;
        Session sess = null;
        if (name != null) {
            try {
                sess = HibernateUtil.getSessionFactory().openSession();
                DetachedCriteria criteria = DetachedCriteria.forClass(Service.class).add(Restrictions.eq("name", name));
                List<Service> serviceList = criteria.getExecutableCriteria(sess).setMaxResults(1).list();
                if (serviceList != null && serviceList.size() > 0) {
                    service = ((serviceList.get(0)));
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
            Logfile.getInstance().getLogger().error(method + "The service name is null.");
            throw new NullPointerException("The service name is null.");
        }
        return service;
    }

}
