package sql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sql.entities.Clasa;
import sql.entities.Elev;
import sql.entities.Nota;
import sql.service.ClasaService;
import sql.service.EleviService;
import sql.service.NoteService;

import java.util.List;

public class HibernateService {

    public static void main(String[] args) {
        ClasaService clasaService = new ClasaService();
        EleviService eleviService = new EleviService();
        NoteService noteService = new NoteService();

        Clasa clasaById = clasaService.findClasaById(2);
        System.out.println(clasaById);

//        Elev elevById = eleviService.findElevById(1); // am citit din baza de date elevul
//
//        SessionFactory instance = SessionFactorySingleton.getInstance();
//        Session session = instance.openSession();
//
//        session.update(elevById);
//
//        System.out.println(elevById);
//        session.close();

//        List<Elev> allElev = eleviService.findAllElevi();
//        System.out.println(allElev);
//
//        Elev elevByName = eleviService.findElevByName("Catalina");
//        System.out.println(elevByName);
//
//        System.out.println("\n\n\nNote:");
//        List<Nota> note = noteService.getNote("Cristi", "Matematica");
//        note.forEach(System.out::println);
//
//
        System.out.println("\n\n\nElevi filtrati:");
        List<Elev> elevi =
                eleviService
                        .filtreazaEleviDupa(null, null, "10");
        SessionFactory instance = SessionFactorySingleton.getInstance();
        Session session = instance.openSession();
        for (Elev elev : elevi) {

            session.update(elev);
            System.out.println(elev);
        }
        session.close();
    }


}
