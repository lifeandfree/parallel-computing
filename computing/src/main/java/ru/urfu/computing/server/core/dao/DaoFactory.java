/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao;

import java.util.IllegalFormatException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import ru.urfu.computing.server.core.dao.person.PersonDAO;
import ru.urfu.computing.server.core.dao.unhandled.UnhandledDAO;
import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;

/**
 * @author lifeandfree
 */
public class DaoFactory {

    private static DaoFactory instance = null;

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            instance = new DaoFactory();
        }

        return instance;
    }

    private ManDAO manDAO = new ManDAO();
    private PersonDAO personDAO = new PersonDAO();
    private UnhandledDAO unhandledDAO = new UnhandledDAO();

    /**
     * @return the manDAO
     */
    public ManDAO getManDAO() {
        return manDAO;
    }

    /**
     * @return the personDAO
     */
    public PersonDAO getPersonDAO() {
        return personDAO;
    }

    /**
     * @return the unhandledDAO
     */
    public UnhandledDAO getUnhandledDAO() {
        return unhandledDAO;
    }

    /**
     * @param unhandledDAO
     *            the unhandledDAO to set
     */
    public void setUnhandledDAO(UnhandledDAO unhandledDAO) {
        this.unhandledDAO = unhandledDAO;
    }

    /**
     * Очистить таблицу
     *
     * @param table
     *            Имя очищаемой таблицы
     */
    public void truncate(String table) {
        Session sess = null;
        try {
            sess = HibernateUtil.getSessionFactory().openSession();
            sess.beginTransaction();
            Query q = sess.createQuery("delete from " + table);
            q.executeUpdate();
        }
        catch (HibernateException e) {
            Logfile.getInstance().getLogger().error(this.getClass().getName() + e.toString());
        }
        catch (IllegalFormatException e) {
            Logfile.getInstance().getLogger().error(this.getClass().getName() + e.toString());
        }
        finally {
            if (sess != null && sess.isOpen()) {
                sess.close();
            }
        }
    }

    /**
     * Очистить все таблицы
     */
    public void truncateAll() {
        truncate(Unhandled.class.getSimpleName());
    }
}
