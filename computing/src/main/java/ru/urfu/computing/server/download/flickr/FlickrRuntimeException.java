/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 21 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

/**
 * @author lifeandfree
 */
public class FlickrRuntimeException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public FlickrRuntimeException() {
    }

    public FlickrRuntimeException(String message) {
        super(message);
    }

    public FlickrRuntimeException(String message, Throwable rootCause) {
        super(message, rootCause);
    }

    public FlickrRuntimeException(Throwable rootCause) {
        super(rootCause);
    }
}
