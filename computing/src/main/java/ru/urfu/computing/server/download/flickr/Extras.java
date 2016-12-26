/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.download.flickr
 *         Дата создания класса: 21 дек. 2016 г.
 */
package ru.urfu.computing.server.download.flickr;

import java.util.HashSet;
import java.util.Set;

/**
 * @author lifeandfree
 */
public class Extras {

    public static final Set<String> ALL_EXTRAS = new HashSet<String>();

    public static final String CAMERA = "camera";

    public static final Set<String> CUSTOM_EXTRAS = new HashSet<String>();

    public static final String DATE_TAKEN = "date_taken";

    public static final String DATE_UPLOAD = "date_upload";

    public static final String GEO = "geo";

    public static final String ICON_SERVER = "icon_server";

    public static final String KEY_EXTRAS = "extras";

    public static final String LAST_UPDATE = "last_update";

    public static final String LICENSE = "license";

    public static final String MACHINE_TAGS = "machine_tags";

    public static final String MEDIA = "media";

    public static final Set<String> MIN_EXTRAS = new HashSet<String>();

    public static final String O_DIMS = "o_dims";

    public static final String ORIGINAL_FORMAT = "original_format";

    public static final String OWNER_NAME = "owner_name";

    public static final String PATH_ALIAS = "path_alias";

    public static final String TAGS = "tags";

    public static final String URL_L = "url_l";

    public static final String URL_M = "url_m";

    public static final String URL_O = "url_o";

    public static final String URL_S = "url_s";

    public static final String URL_SQ = "url_sq";

    public static final String URL_T = "url_t";

    public static final String VIEWS = "views";

    static {
        ALL_EXTRAS.add(DATE_TAKEN);
        ALL_EXTRAS.add(DATE_UPLOAD);
        ALL_EXTRAS.add(ICON_SERVER);
        ALL_EXTRAS.add(LAST_UPDATE);
        ALL_EXTRAS.add(LICENSE);
        ALL_EXTRAS.add(ORIGINAL_FORMAT);
        ALL_EXTRAS.add(OWNER_NAME);
        ALL_EXTRAS.add(GEO);
        ALL_EXTRAS.add(TAGS);
        ALL_EXTRAS.add(MACHINE_TAGS);
        ALL_EXTRAS.add(O_DIMS);
        ALL_EXTRAS.add(MEDIA);
        ALL_EXTRAS.add(VIEWS);
        ALL_EXTRAS.add(PATH_ALIAS);
        ALL_EXTRAS.add(URL_S);
        ALL_EXTRAS.add(URL_SQ);
        ALL_EXTRAS.add(URL_T);
        ALL_EXTRAS.add(URL_M);
        ALL_EXTRAS.add(URL_O);
        ALL_EXTRAS.add(URL_L);
        ALL_EXTRAS.add(CAMERA);
    }

    static {
        MIN_EXTRAS.add(ORIGINAL_FORMAT);
        MIN_EXTRAS.add(OWNER_NAME);
    }

    static {
        CUSTOM_EXTRAS.add(DATE_TAKEN);
        CUSTOM_EXTRAS.add(DATE_UPLOAD);
        CUSTOM_EXTRAS.add(LAST_UPDATE);
        CUSTOM_EXTRAS.add(OWNER_NAME);
        CUSTOM_EXTRAS.add(GEO);
        CUSTOM_EXTRAS.add(TAGS);
        CUSTOM_EXTRAS.add(MACHINE_TAGS);
        CUSTOM_EXTRAS.add(VIEWS);
        CUSTOM_EXTRAS.add(PATH_ALIAS);
        CUSTOM_EXTRAS.add(URL_S);
        CUSTOM_EXTRAS.add(URL_SQ);
        CUSTOM_EXTRAS.add(URL_T);
        CUSTOM_EXTRAS.add(URL_M);
        CUSTOM_EXTRAS.add(URL_O);
        CUSTOM_EXTRAS.add(URL_L);
        CUSTOM_EXTRAS.add(CAMERA);
    }

    /**
     *
     */
    private Extras() {
    }

}
