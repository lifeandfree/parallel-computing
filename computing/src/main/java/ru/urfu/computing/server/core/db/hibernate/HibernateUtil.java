/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.hibernate
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.db.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import ru.urfu.computing.Application;
import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author Сухачев Илья
 *         20 нояб. 2016 г.
 *         Фабрика по созданию соединений для подключения к БД c помощью
 *         hibernate.
 */
public class HibernateUtil {
    private static ServiceRegistry serviceRegistry;
    private static final SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        Configuration configuration = null;
        try {
            configuration = new Configuration();
            configuration.configure();
            serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            Logfile.getInstance().getLogger()
                    .info(Application.class.getName() + ": Create a configuration for connection");
        }
        catch (Exception e) {
            Logfile.getInstance().getLogger()
                    .error(Application.class.getName() + ": I can not create a configuration for connection", e);
            throw new ExceptionInInitializerError(e);
        }

        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
