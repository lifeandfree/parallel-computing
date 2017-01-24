/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.utils
 *         Дата создания класса: 24 янв. 2017 г.
 */
package ru.urfu.computing.server.utils;

import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class Utils {

    /**
     *
     */
    public Utils() {
    }

    /**
     * Преобразовать строку в double
     *
     * @param parameter
     *            - строка для преобразования.
     * @return - {@link Double}
     */
    public double getDoubleParameter(String parameter) throws RuntimeException {
        double param = 0;
        if (parameter != null && !parameter.trim().equals("")) {
            try {
                param = Double.parseDouble(parameter);
            }
            catch (NumberFormatException e) {
                Logfile.getInstance().getLogger().error("Could not convert " + parameter + " in Double format", e);
                throw new RuntimeException("Could not convert " + parameter + " in Double format");
            }
        }
        else {
            Logfile.getInstance().getLogger().error("Come empty string");
            throw new RuntimeException("Come empty string");
        }
        return param;
    }

}
