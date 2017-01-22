/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.db.dao.element
 *         Дата создания класса: 20 нояб. 2016 г.
 */
package ru.urfu.computing.server.core.dao.element;

import java.util.Collection;

/**
 * @author lifeandfree
 */
public interface ElementDAO<E> {

    public E addElement(E el);

    public void deleteElement(E el);

    public Collection<E> getAllElements();

    public E getElementByID(Long elId);

    public Collection<E> getLimitElements(int firstResult, int maxResults);

    public long getSizeOfTable();

    public void updateElement(E el);

}
