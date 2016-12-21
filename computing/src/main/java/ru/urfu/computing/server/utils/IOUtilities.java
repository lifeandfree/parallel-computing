/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.utils
 *         Дата создания класса: 19 дек. 2016 г.
 */
package ru.urfu.computing.server.utils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author lifeandfree
 */
public class IOUtilities {

    public static void close(InputStream s) {
        if (s != null) {
            try {
                s.close();
            }
            catch (IOException e) {

            }
        }
    }

    public IOUtilities() {
    }

}
