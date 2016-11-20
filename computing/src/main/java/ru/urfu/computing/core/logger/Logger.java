/**
 *
 */
package ru.urfu.computing.core.logger;

import org.apache.logging.log4j.LogManager;

/**
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
