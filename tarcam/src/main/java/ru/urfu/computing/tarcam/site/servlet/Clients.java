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
import ru.urfu.computing.server.core.model.camera.Camera;
import ru.urfu.computing.server.core.model.person.Person;
import ru.urfu.computing.server.core.model.relation.Relation;

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

        Collection<Relation> relations = DaoFactory.getInstance().getRelationDAO().getByCameraId(camera.getId(), 1000);
        ArrayList<Person> persons = new ArrayList<>();
        for (Relation relation : relations) {
            Person person = DaoFactory.getInstance().getPersonDAO().getElementByID(relation.getPerson_id());
            if (person != null) {
                persons.add(person);
            }
        }

        String typeShape = req.getParameter("t");

        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        if (typeShape == null) {
            if (persons.isEmpty()) {
                sb.append("\"error\", \"clients\": [ ]}");

            }
            else {
                sb.append("\"error\": 0, \"clients\": [ ");
            }
            for (Person person : persons) {
                sb.append("\"flickr.com/" + person.getName() + "\", ");
            }
            sb.deleteCharAt(sb.length() - 2);
            sb.append(" ] }");
        }

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
