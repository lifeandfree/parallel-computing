/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.unhandled
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao.unhandled;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.model.unhandled.Unhandled;

/**
 * @author lifeandfree
 */
public class UnhandledDAO extends ElementDAOImpl<Unhandled> {

    public UnhandledDAO() {
        super(Unhandled.class);
    }

    public UnhandledDAO(Class<Unhandled> elementClass) {
        super(elementClass);
    }
}
