/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr.transfer.rest
 *         Дата создания класса: 20 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Verb;

import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class Rest extends Transport {

    public static final String PATH = "/services/rest/";
    private Integer connectTimeoutMs;
    private Integer readTimeoutMs;

    public Rest() {
        setTransportType(REST);
        setHost(API_HOST);
        setPath(PATH);
        setScheme(DEFAULT_SCHEME);
        setResponseClass(RESTResponse.class);
    }

    @Override
    public ru.urfu.computing.server.download.flickr.Response get(String path, Map<String, Object> parameters,
            String apiKey, String sharedSecret) throws FlickrException {
        OAuthRequest request = new OAuthRequest(Verb.GET, getScheme() + "://" + getHost() + path);
        for (Map.Entry<String, Object> entry : parameters.entrySet()) {
            request.addQuerystringParameter(entry.getKey(), String.valueOf(entry.getValue()));
        }

        if (!parameters.containsKey(Flickr.API_KEY)) {
            request.addQuerystringParameter(Flickr.API_KEY, apiKey);
        }

        setTimeouts(request);
        org.scribe.model.Response scribeResponse = request.send();

        Response response = null;
        String strRes = scribeResponse.getBody().trim();
        strRes.toString();
        if (strRes.startsWith("oauth_problem=")) {
            try {
                throw new FlickrRuntimeException(strRes);
            }
            catch (FlickrRuntimeException e) {
                Logfile.getInstance().getLogger().error(e);
            }
        }
        return response;
    }

    private void setTimeouts(OAuthRequest request) {
        if (connectTimeoutMs != null) {
            request.setConnectTimeout(connectTimeoutMs, TimeUnit.MILLISECONDS);
        }
        if (readTimeoutMs != null) {
            request.setReadTimeout(readTimeoutMs, TimeUnit.MILLISECONDS);
        }
    }

}
