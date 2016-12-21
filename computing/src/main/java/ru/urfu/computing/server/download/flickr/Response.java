/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 19 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import org.w3c.dom.Document;

/**
 * @author lifeandfree
 */
public interface Response {

    String getErrorCode();

    String getErrorMessage();

    boolean isError();

    void parse(Document document);
}
