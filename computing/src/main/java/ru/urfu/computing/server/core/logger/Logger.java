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
public class Logger {

    private static Logger instance = null;

    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }

        return instance;
    }

    private org.apache.logging.log4j.Logger logger = LogManager.getLogger(Logger.class.getName());

    /**
     *
     */
    public Logger() {
        super();
    }

    public org.apache.logging.log4j.Logger getLogger() {
        return logger;
    }

    public void setLogger(org.apache.logging.log4j.Logger logger) {
        this.logger = logger;
    }

}
