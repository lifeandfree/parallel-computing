/**
 * Организация: УрФУ
 * Проект: Параллельные вычисления, 2016
 *
 * @author lifeandfree
 *         Пакет: ru.urfu.computing.tarcam.site.servlet
 *         Дата создания класса: 19 янв. 2017 г.
 */
package ru.urfu.computing.tarcam.site.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lifeandfree
 */
public class MainPage extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = -1924984265408926223L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setAttribute("title", "tarcam");
    	//StringBuilder sb = new StringBuilder("")
        req.setAttribute("body", "tarcam111111111111111111111");
        req.getRequestDispatcher("main_page_template.jsp").forward(req, resp);  
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        super.doPost(req, resp);
    }   

}
