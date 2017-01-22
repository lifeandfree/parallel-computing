/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing;

import ru.urfu.computing.server.download.flickr.Flickr;

/**
 * @author lifeandfree
 */
public class DownloadApp {

    public static void main(String[] args) {
        // long min_upload_date = 1482519900;
        long min_upload_date = 1483819900;
        // long max_upload_date = 1482521700;
        long max_upload_date = 1483906300;
        long interval = 600;
        Flickr flickr = new Flickr();
        flickr.getAndWriteUnhanledToDB(min_upload_date, max_upload_date, interval);
    }

    /**
     *
     */
    public DownloadApp() {
    }

}
