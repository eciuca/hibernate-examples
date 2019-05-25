package sql.controller;

import sql.entities.Clasa;
import sql.service.ClasaService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/clase")
public class ClaseController extends HttpServlet {


    private final ClasaService clasaService;

    public ClaseController() {
        this.clasaService = new ClasaService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("<html><head></head><body>");
        writer.println("<p>Clase</p>");

        String id = req.getParameter("id");
        if (id != null && !id.isEmpty()) {
            writer.println("<span>");
            Clasa clasaById = clasaService.findClasaById(Integer.parseInt(id));
            if (clasaById == null) {
                req.setAttribute("notFoundObject", "Clasa cu id " + id);
                getServletContext().getRequestDispatcher("/notfound").forward(req, resp);
                return;
            } else {
                writer.println(clasaById);
                writer.println("</span>");
            }
        } else {
            writer.println("<ul>");
            for (Clasa clasa : clasaService.findAllClase()) {
                writer.println("<li>" + clasa.toString() + "</li>");
            }
        }
        writer.println("</ul>");
        writer.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nume = req.getParameter("nume");

        clasaService.addClasa(nume);
    }
}
