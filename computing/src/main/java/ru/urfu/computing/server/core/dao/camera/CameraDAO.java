/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.camera
 *         Дата создания класса: 25 дек. 2016 г.
 */
package ru.urfu.computing.server.core.dao.camera;

import ru.urfu.computing.server.core.dao.element.ElementDAOImpl;
import ru.urfu.computing.server.core.model.camera.Camera;

/**
 * @author lifeandfree
 */
public class CameraDAO extends ElementDAOImpl<Camera> {

    public CameraDAO() {
        super(Camera.class);
    }

    /**
     * @param elementClass
     */
    public CameraDAO(Class<Camera> elementClass) {
        super(elementClass);
    }

}
