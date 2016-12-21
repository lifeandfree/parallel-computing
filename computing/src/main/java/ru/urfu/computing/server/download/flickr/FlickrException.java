/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 19 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

/**
 * @author lifeandfree
 */
public class FlickrException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 8398522439246519181L;

    private String errorCode;

    private String errorMessage;

    public FlickrException(String errorMessage) {

        super(errorMessage);
    }

    public FlickrException(String errorCode, String errorMessage) {
        super(errorCode + ": " + errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public FlickrException(String errorMessage, Throwable rootCause) {

        super(errorMessage, rootCause);
    }

    public FlickrException(Throwable rootCause) {

        super(rootCause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
