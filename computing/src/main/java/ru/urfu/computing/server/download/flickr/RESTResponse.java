/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 21 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import org.w3c.dom.Document;

/**
 * @author lifeandfree
 */
public class RESTResponse implements Response {

    private String errorCode;

    private String errorMessage;

    private String stat;

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    public String getStat() {
        return stat;
    }

    @Override
    public boolean isError() {
        return errorCode != null;
    }

    @Override
    public void parse(Document document) {
    }

}
