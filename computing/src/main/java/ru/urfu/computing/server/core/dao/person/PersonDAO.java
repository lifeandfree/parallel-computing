/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao
 *         Дата создания класса: 23 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao.person;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.model.person.Person;

/**
 * @author lifeandfree
 */
public class PersonDAO extends ElementDAOImpl<Person> {

    public PersonDAO() {
        super(Person.class);
    }

    public PersonDAO(Class<Person> elementClass) {
        super(elementClass);
    }

}
