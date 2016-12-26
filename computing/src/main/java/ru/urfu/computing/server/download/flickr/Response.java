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
public abstract class Response {

    private long currentPage;
    private String errorCode;
    private String errorMessage;
    private Parser parser;
    private Object photos;
    private String stat;
    private long totalPages;
    public static final int MAX_PAGES = 16;

    public long getCurrentPage() {
        return currentPage;
    }

    /**
     * @return the errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @return the parser
     */
    public Parser getParser() {
        return parser;
    }

    public Object getPhotos() {
        return photos;
    }

    /**
     * @return the stat
     */
    public String getStat() {
        return stat;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @param errorCode
     *            the errorCode to set
     */
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    /**
     * @param errorMessage
     *            the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * @param parser
     *            the parser to set
     */
    public void setParser(Parser parser) {
        this.parser = parser;
    }

    public void setPhotos(Object photos) {
        this.photos = photos;
    }

    /**
     * @param stat
     *            the stat to set
     */
    public void setStat(String stat) {
        this.stat = stat;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }

}
