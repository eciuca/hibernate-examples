package sql.controllers;

import sql.entities.Elev;
import sql.service.ClasaService;
import sql.service.EleviService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/first")
public class MyFirstServlet extends HttpServlet {

    private final EleviService eleviService;

    public MyFirstServlet() {
        eleviService = new EleviService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.println("Salut din MyFirstServlet!");
        List<Elev> allElevi = eleviService.findAllElevi();

        for (Elev elev : allElevi) {
            writer.println("Elev: " + elev.getNumeElev());
        }
    }
}
