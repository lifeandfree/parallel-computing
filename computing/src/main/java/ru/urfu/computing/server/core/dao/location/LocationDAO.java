/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.location
 *         Дата создания класса: 25 дек. 2016 г.
 */
package ru.urfu.computing.server.core.dao.location;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.model.location.Location;

/**
 * @author lifeandfree
 */
public class LocationDAO extends ElementDAOImpl<Location> {

    public LocationDAO() {
        super(Location.class);
    }

    /**
     * @param elementClass
     */
    public LocationDAO(Class<Location> elementClass) {
        super(elementClass);
    }

}
