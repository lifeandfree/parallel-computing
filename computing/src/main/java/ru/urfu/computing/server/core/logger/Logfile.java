/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author Сухачев Илья
 *         Пакет: ru.urfu.computing.server.core.logger
 *         Дата создания класса: 19 ноября 2016 г.
 */
package ru.urfu.computing.server.core.logger;

import org.apache.logging.log4j.LogManager;

/**
 * Логер
 *
 * @author lifeandfree
 */
public class Logfile {

    private static Logfile instance = null;

    public static synchronized Logfile getInstance() {
        if (instance == null) {
            instance = new Logfile();
        }

        return instance;
    }

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logfile.class.getName());

    /**
     *
     */
    public Logfile() {
        super();
    }

    public org.apache.logging.log4j.Logger getLogger() {
        return logger;
    }

    public void setLogger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

}
