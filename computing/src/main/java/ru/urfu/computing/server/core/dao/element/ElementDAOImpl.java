/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.dao.element
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao.element;

import java.util.Collection;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import ru.urfu.computing.server.core.db.hibernate.HibernateUtil;
import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class ElementDAOImpl<E> implements ElementDAO<E> {

    private Class<E> elementClass;

    public ElementDAOImpl(Class<E> elementClass) {
        this.elementClass = elementClass;
    }

    /**
     * Добавляет элемент в БД.
     *
     * @param el
     *            - элемент для добавления.
     */
    @Override
    public E addElement(E el) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(el);
            transaction.commit();
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger()
                    .error(HibernateUtil.class.getName() + "I can not add an item to the database", e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return el;
    }

    /**
     * Удаляет элемент в БД.
     *
     * @param el
     *            - элемент для удаления.
     */
    @Override
    public void deleteElement(E el) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(el);
            transaction.commit();
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger()
                    .error(HibernateUtil.class.getName() + "I can not remove an item to the database", e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    /**
     * Запращивает все элементы из БД.
     *
     * @return {@link Collection} элементов {@link E} из БД.
     */
    @Override
    public Collection<E> getAllElements() {
        List<E> list = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Transaction transaction = session.beginTransaction();
            // DetachedCriteria criteria = DetachedCriteria.forClass(elementClass);
            // list = criteria.getExecutableCriteria(session).list();
            // TODO
            list = session.createCriteria(elementClass).list();
            // transaction.commit();
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger()
                    .error(HibernateUtil.class.getName() + "I can not query all items in the database", e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return list;
    }

    /**
     * Запращивает элемент по Id.
     *
     * @param elId
     *            - элемент для запроса.
     * @return элемент {@link E} из БД.
     */
    @Override
    public E getElementByID(Long elId) {
        E el = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            // Transaction transaction = session.beginTransaction();
            el = session.get(elementClass, elId);
            // transaction.commit();
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger()
                    .error(HibernateUtil.class.getName() + "I can not query item in the database", e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return el;
    }

    /**
     * Обновляет элемент в БД.
     *
     * @param el
     *            - элемент для добавления.
     */
    @Override
    public void updateElement(E el) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(el);
            transaction.commit();
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger()
                    .error(HibernateUtil.class.getName() + "I can not update an item to the database", e);
        }
        finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

}
