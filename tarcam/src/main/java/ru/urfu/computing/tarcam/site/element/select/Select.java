/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.tarcam.site.element.select
 *         Дата создания класса: 22 янв. 2017 г.
 */
package ru.urfu.computing.tarcam.site.element.select;

import java.util.Collection;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.model.camera.Camera;

/**
 * @author lifeandfree
 */
public class Select {

    private String select;

    /**
     *
     */
    public Select() {
        select = null;
    }

    public String getSelectFromCameraList() {
        select = "";
        StringBuilder sb = new StringBuilder("");
        sb.append("<select>");
        Collection<Camera> cameras = DaoFactory.getInstance().getCameraDAO().getAllElements();
        for (Camera camera : cameras) {
            sb.append("<option value=\"" + camera.getId() + "\">" + camera.getName() + "</option>");
        }
        sb.append("</select>");
        select = sb.toString();
        return select;

    }

}
