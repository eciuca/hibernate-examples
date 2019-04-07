package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sql.entities.Materie;
import sql.entities.Profesor;
import sql.SessionFactorySingleton;

public class ProfesoriService {

    private SessionFactory sessionFactory;
    private MaterieService materieService;

    public ProfesoriService(){
        this.materieService = new MaterieService();
        this.sessionFactory = SessionFactorySingleton.getInstance();
    }

    public Profesor findProfesorById(int id){
        Session session = sessionFactory.openSession();

        Profesor foundProfesor = session.find(Profesor.class, id);

        session.close();

        return foundProfesor;
    }

    public void deleteProfesorById(int id){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Profesor profesorById = findProfesorById(id);

        if (profesorById == null){
            System.out.println("Profesor " + id + " not found");

            return;
        }

        session.delete(profesorById);

        transaction.commit();
        session.close();
    }

    public Profesor addProfesor(String numeProfesor, int idMaterie){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Materie materie = materieService.findMaterieById(idMaterie);

        Profesor profesor = new Profesor(numeProfesor, materie);
        session.persist(profesor);

        transaction.commit();
        session.close();

        return profesor;
    }
}
