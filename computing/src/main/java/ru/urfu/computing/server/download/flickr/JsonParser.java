/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 22 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import ru.urfu.computing.server.core.logger.Logfile;

/**
 * @author lifeandfree
 */
public class JsonParser extends Parser {

    public Response response;

    /**
     *
     */
    public JsonParser() {

    }

    public JsonParser(Response response) {
        this.response = response;
    }

    @Override
    public Response getData(String data) {
        if (response == null) {
            try {
                throw new FlickrException("Response cannot equels null");
            }
            catch (FlickrException e) {
                Logfile.getInstance().getLogger().error(e);
            }
            return null;
        }
        data = data.substring(14, data.length() - 1);
        JSONParser jsonParser = new JSONParser();
        try {
            Object obj = jsonParser.parse(data);
            JSONObject jsonObject = (JSONObject)obj;
            String stat = (String)jsonObject.get("stat");
            response.setStat(stat);
            if (stat.equals("ok")) {
                JSONObject photos = (JSONObject)jsonObject.get("photos");
                Long totalPages = (Long)photos.get("pages");
                response.setTotalPages(totalPages);
                JSONArray photoAr = (JSONArray)photos.get("photo");
                response.setPhotos(photoAr);
            }
            else {
                try {
                    throw new FlickrRuntimeException(data);
                }
                catch (FlickrRuntimeException e) {
                    Logfile.getInstance().getLogger().error(e);
                }
            }
        }
        catch (ParseException e) {
            Logfile.getInstance().getLogger().error(e);
        }
        return this.response;
    }

    /**
     * @return the response
     */
    public Response getResponse() {
        return response;
    }

    /**
     * @param response
     *            the response to set
     */
    public void setResponse(Response response) {
        this.response = response;
    }

}
