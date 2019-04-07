package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sql.entities.Materie;
import sql.entities.Profesori;
import sql.SessionFactorySingleton;

public class ProfesoriService {

    private SessionFactory sessionFactory;
    private MaterieService materieService;

    public ProfesoriService(){
        this.materieService = new MaterieService();
        this.sessionFactory = SessionFactorySingleton.getInstance();
    }

    public Profesori findProfesorById(int id){
        Session session = sessionFactory.openSession();

        Profesori foundProfesor = session.find(Profesori.class, id);

        session.close();

        return foundProfesor;
    }

    public void deleteProfesorById(int id){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Profesori profesorById = findProfesorById(id);

        if (profesorById == null){
            System.out.println("Profesor " + id + " not found");

            return;
        }

        session.delete(profesorById);

        transaction.commit();
        session.close();
    }

    public Profesori addProfesor(String numeProfesor, int idMaterie){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Materie materie = materieService.findMaterieById(idMaterie);

        Profesori profesori = new Profesori(numeProfesor, materie);
        session.persist(profesori);

        transaction.commit();
        session.close();

        return profesori;
    }
}
