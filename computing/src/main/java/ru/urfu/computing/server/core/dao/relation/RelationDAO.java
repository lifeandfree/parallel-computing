/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.relation
 *         Дата создания класса: 13 дек. 2016 г.
 */
package ru.urfu.computing.server.core.dao.relation;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.model.relation.Relation;

/**
 * @author lifeandfree
 */
public class RelationDAO extends ElementDAOImpl<Relation> {

    public RelationDAO() {
        super(Relation.class);
    }

    public RelationDAO(Class<Relation> elementClass) {
        super(elementClass);
    }

}
