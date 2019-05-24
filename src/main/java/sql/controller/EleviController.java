package sql.controller;

import sql.entities.Elev;
import sql.service.EleviService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/elevi")
public class EleviController extends HttpServlet {

    private final EleviService eleviService;

    public EleviController() {
        this.eleviService = new EleviService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();




        writer.println("<html><head></head><body>");
        writer.println("<p>Hello</p>");

        String id= req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            writer.println("<span>");
            writer.println(eleviService.findElevById(Integer.parseInt(id)));
            writer.println("</span>");
        } else {
            writer.println("<ul>");
            for (Elev elev : eleviService.findAllElevi()) {
                writer.println("<li>" + elev.toString() + "</li>");
            }
        }
        writer.println("</ul>");
        writer.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nume = req.getParameter("nume");
        int idClasa = 1;

        eleviService.addElev(nume, 1);

    }
}
