/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao
 *         Дата создания класса: 13 дек. 2016 г.
 */
package ru.urfu.computing.server.core.dao;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.model.man.Man;

/**
 * @author lifeandfree
 */
public class ManDAO extends ElementDAOImpl<Man> {

    public ManDAO() {
        super(Man.class);
    }

    public ManDAO(Class<Man> elementClass) {
        super(elementClass);
    }

}
