package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sql.entities.Materie;
import sql.SessionFactorySingleton;

public class MaterieService {

    private SessionFactory sessionFactory;

    public MaterieService(){
        this.sessionFactory = SessionFactorySingleton.getInstance();
    }

    public Materie findMaterieById(int id){
        Session session = sessionFactory.openSession();

        Materie foundMaterie = session.find(Materie.class, id);

        session.close();

        return foundMaterie;
    }

    public void deleteMaterieById(int id){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Materie materieById = findMaterieById(id);

        if (materieById == null){
            System.out.println("Materia " + id + " not found");
            return;
        }

        session.delete(materieById);

        transaction.commit();
        session.close();
    }

    public Materie addMaterie(String numeMaterie){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Materie materie = new Materie(numeMaterie);
        session.persist(materie);

        transaction.commit();
        session.close();

        return materie;

    }


    public Materie findMaterieByName(String name) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery(
                "select m from Materie m where m.numeMaterie = :name");

        query.setParameter("name", name);

        Materie materie = (Materie) query.uniqueResult();
        transaction.commit();
        session.close();

        return materie;

    }
}
