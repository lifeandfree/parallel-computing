/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.server.core.dao.photo
 *         Дата создания класса: 21 янв. 2017 г.
 */
package ru.urfu.computing.server.core.dao.photo;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.db.jdbc.model.photo.Photo;
import ru.urfu.computing.server.core.model.camera.Camera;
import ru.urfu.computing.server.core.model.location.Location;
import ru.urfu.computing.server.core.model.person.Person;
import ru.urfu.computing.server.core.model.relation.Relation;
import ru.urfu.computing.server.core.model.service.Service;

/**
 * @author lifeandfree
 */
public class PhotoDAO {

    /**
     *
     */
    public PhotoDAO() {
    }

    public void writePhotoToBD(Photo photo) {
        Service service = DaoFactory.getInstance().getServiceDAO().getByName(photo.getService());
        long serviceId = 0L;
        if (service != null) {
            serviceId = service.getId();
        }
        else {
            service = DaoFactory.getInstance().getServiceDAO().addElement(new Service(photo.getService()));
            serviceId = service.getId();
        }

        Person person = DaoFactory.getInstance().getPersonDAO().getByName(photo.getOwner(), serviceId);
        long personId = 0L;
        if (person != null) {
            personId = person.getId();
        }
        else {
            person = DaoFactory.getInstance().getPersonDAO().addElement(new Person(photo.getOwner(), serviceId));
            personId = person.getId();
            DaoFactory.getInstance().getLocationDAO().addElement(
                    new Location(Double.valueOf(photo.getLatitude()), Double.valueOf(photo.getLongitude()), personId));
        }

        Camera camera = DaoFactory.getInstance().getCameraDAO().getByName(photo.getCamera());
        long cameraId = 0L;
        if (camera != null) {
            cameraId = camera.getId();
        }
        else {
            camera = DaoFactory.getInstance().getCameraDAO().addElement(new Camera(photo.getCamera()));
            cameraId = camera.getId();
        }
        Relation relation = DaoFactory.getInstance().getRelationDAO().getByPair(cameraId, personId);
        if (relation == null) {
            DaoFactory.getInstance().getRelationDAO().addElement(new Relation(cameraId, personId));
        }

    }

}
