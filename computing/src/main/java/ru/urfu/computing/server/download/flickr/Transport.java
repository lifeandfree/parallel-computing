/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr.transfer.transport
 *         Дата создания класса: 20 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import java.util.Map;

/**
 * @author lifeandfree
 */
public abstract class Transport {

    protected static final String API_HOST = "api.flickr.com";

    protected static final String DEFAULT_SCHEME = "https";

    public static final String REST = "REST";

    private String host;

    private String path;

    protected Class<?> responseClass;

    private String scheme;

    private String transportType;

    public abstract Response get(String path, Map<String, Object> parameters, String apiKey, String sharedSecret)
            throws FlickrException;

    public String getHost() {
        return host;
    }

    public String getPath() {
        return path;
    }

    /**
     * @return the responseClass
     */
    public Class<?> getResponseClass() {
        return responseClass;
    }

    /**
     * @return the scheme
     */
    public String getScheme() {
        return scheme;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @param responseClass
     *            the responseClass to set
     */
    public void setResponseClass(Class<?> responseClass) {
        this.responseClass = responseClass;
    }

    /**
     * @param scheme
     *            the scheme to set
     */
    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

}
