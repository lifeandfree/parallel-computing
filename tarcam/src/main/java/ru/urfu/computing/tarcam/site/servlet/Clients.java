/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.tarcam.site.servlet
 *         Дата создания класса: 22 янв. 2017 г.
 */
package ru.urfu.computing.tarcam.site.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ru.urfu.computing.server.core.dao.DaoFactory;
import ru.urfu.computing.server.core.dao.person.PersonJDBC;
import ru.urfu.computing.server.core.db.jdbc.model.database.DatabaseImpl;
import ru.urfu.computing.server.core.model.camera.Camera;
import ru.urfu.computing.server.core.model.person.Person;
import ru.urfu.computing.server.core.model.relation.Relation;
import ru.urfu.computing.server.utils.Utils;

/**
 * @author lifeandfree
 */
public class Clients extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -5805954029266978138L;

    /**
     *
     */
    public Clients() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String modelTel = req.getParameter("tm");
        if (modelTel == null) {
            out.print("{\"error\": 2 }");
        }

        Long modelTelL = Long.valueOf(modelTel);

        Camera camera = DaoFactory.getInstance().getCameraDAO().getElementByID(modelTelL);

        StringBuilder sb = new StringBuilder();
        String typeShape = req.getParameter("t");
        sb.append("{ ");
        if (typeShape == null) {

            Collection<Relation> relations = DaoFactory.getInstance().getRelationDAO().getByCameraId(camera.getId(),
                    10000);
            ArrayList<Person> persons = new ArrayList<>();
            for (Relation relation : relations) {
                Person person = DaoFactory.getInstance().getPersonDAO().getElementByID(relation.getPerson_id());
                if (person != null) {
                    persons.add(person);
                }
            }

            if (persons.isEmpty()) {
                sb.append("\"error\": 1, \"clients\": [ ]}");

            }
            else {
                sb.append("\"error\": 0, \"clients\": [ ");
            }
            for (Person person : persons) {
                sb.append("\"flickr.com/" + person.getName() + "\", ");
            }
            sb.deleteCharAt(sb.length() - 2);

        }
        else {
            boolean flagSim = true;
            PersonJDBC personJDBC = new PersonJDBC(
                    new DatabaseImpl("192.168.1.41", 5432, "computing3", "computing", "123", "postgresql"));
            ArrayList<String> personNames = new ArrayList<>();
            double fprx;
            double fpry;
            double sprx;
            double spry;
            if (typeShape.equals("c")) {
                double centerX = new Utils().getDoubleParameter(req.getParameter("ccx"));
                double centerY = new Utils().getDoubleParameter(req.getParameter("ccy"));
                double radius = new Utils().getDoubleParameter(req.getParameter("cr")) /1000;

                double delta = (radius  / (111.134861111));
                fpry = centerY - delta;
                spry = centerY + delta;
                double gradekv = 40075.696 / 360;
                double deltaX = (radius / (gradekv * Math.cos(centerX)));

                fprx = centerX - deltaX;
                sprx = centerX + deltaX;
            }
            else {
                //
                // //rect
                if (typeShape.equals("r")) {
                    fprx = new Utils().getDoubleParameter(req.getParameter("fprx"));
                    fpry = new Utils().getDoubleParameter(req.getParameter("fpry"));
                    sprx = new Utils().getDoubleParameter(req.getParameter("sprx"));
                    spry = new Utils().getDoubleParameter(req.getParameter("spry"));
                }
                else {
                    flagSim = false;
                    fprx = 0;
                    fpry = 0;
                    sprx = 0;
                    spry = 0;
                }
            }
            if (flagSim) {
                personNames = personJDBC.getPersonByGeoJDBC(camera.getId(), fprx, fpry, sprx, spry);
            }

            if (personNames.isEmpty()) {
                sb.append("\"error\": 1, \"clients\": [  ");

            }
            else {
                sb.append("\"error\": 0, \"clients\": [ ");
            }
            for (String personName : personNames) {
                sb.append("\"flickr.com/" + personName + "\", ");
            }
            sb.deleteCharAt(sb.length() - 2);
        }
        sb.append(" ] }");

        out.print(sb.toString());
        //
        // //circle
        // String centerX = req.getParameter("ccx");
        // String centerY = req.getParameter("ccy");
        // String radius = req.getParameter("cr");
        //
        // //rect
        // String fprx = req.getParameter("fprx");
        // String fpry = req.getParameter("fpry");
        // String sprx = req.getParameter("sprx");
        // String spry = req.getParameter("spry");

    }

}
