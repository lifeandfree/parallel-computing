/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 21 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.logger.Logfile;
import ru.urfu.computing.server.utils.IOUtilities;

/**
 * @author lifeandfree
 */
public class Flickr {

    public static final String API_KEY = "api_key";

    public Flickr() {
    }

    /**
     * @param min_upload_date
     * @param max_upload_date
     * @param interval
     * @return
     */
    public void getAndWriteUnhanledToDB(long min_upload_date, long max_upload_date, long interval) {
        while (min_upload_date < max_upload_date) {
            getAndWriteUnhanledToDBByTime(min_upload_date, min_upload_date + interval);
            min_upload_date += interval;
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                Logfile.getInstance().getLogger().error(e);
            }
        }
    }

    /**
     * @param apiKey
     * @param sharedSecret
     * @param parameters
     * @param transport
     */
    public void getAndWriteUnhanledToDBByPage(String apiKey, String sharedSecret, Map<String, Object> parameters,
            Transport transport) {
        try {
            Response response = transport.get(transport.getPath(), parameters, apiKey, sharedSecret);
            JSONArray photos = (JSONArray)response.getPhotos();
            for (Object el : photos) {
                JSONObject photo = (JSONObject)el;
                String id = (String)photo.get("id");
                DaoFactory.getInstance().getUnhandledDAO().addUnhandled(id, photo.toString());
            }
            response.setCurrentPage(response.getCurrentPage() + 1);
            if ((response.getCurrentPage() <= response.getTotalPages())
                    && (response.getCurrentPage() <= Response.MAX_PAGES)) {
                parameters.put("page", response.getCurrentPage());

                getAndWriteUnhanledToDBByPage(apiKey, sharedSecret, parameters, transport);
                try {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    Logfile.getInstance().getLogger().error(e);
                }
            }
        }
        catch (FlickrException e) {
            Logfile.getInstance().getLogger().error(e);
        }
    }

    /**
     * @param min_upload_date
     * @param max_upload_date
     */
    public void getAndWriteUnhanledToDBByTime(long min_upload_date, long max_upload_date) {
        // params = "{per_page=10, method=flickr.photos.search, user_id=59159233@N04, format=json,
        // extras=url_t,owner_name,machine_tags,date_upload,url_s,o_dims,media,url_sq,tags,geo,license,original_format,path_alias,last_update,url_o,icon_server,url_l,url_m,camera,date_taken,views,
        // page=1}";

        Properties properties = null;
        InputStream in = null;
        String apiKey = null;
        String sharedSecret = null;
        try {
            in = Test.class.getResourceAsStream("/auth.properties");
            properties = new Properties();
            properties.load(in);
            apiKey = properties.getProperty("apiKey");
            sharedSecret = properties.getProperty("sharedSecret");
            properties.getProperty("user_id");
        }
        catch (IOException e) {
            Logfile.getInstance().getLogger().error(e);
        }
        finally {
            IOUtilities.close(in);
        }

        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("per_page", "250");
        parameters.put("method", "flickr.photos.search");
        parameters.put("format", "json");
        parameters.put("has_geo", "1");
        parameters.put("extras", "camera,geo");
        parameters.put("page", "1");
        parameters.put("media", "photo");
        parameters.put("content_type", "1");
        // parameters.put("extras",
        // "url_t,owner_name,machine_tags,date_upload,url_s,o_dims,media,url_sq,tags,geo,license,original_format,path_alias,last_update,url_o,icon_server,url_l,url_m,camera,date_taken,views");
        // java.sql.Timestamp timestamp = new java.sql.Timestamp(1482521700000L);

        parameters.put("min_upload_date", min_upload_date);

        parameters.put("max_upload_date", max_upload_date);

        Transport transport = new Rest();

        getAndWriteUnhanledToDBByPage(apiKey, sharedSecret, parameters, transport);
    }

}
