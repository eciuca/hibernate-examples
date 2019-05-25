package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sql.SessionFactorySingleton;
import sql.entities.Clasa;

import java.util.List;

public class ClasaService {

    private SessionFactory sessionFactory;

    public ClasaService() {
        this.sessionFactory = SessionFactorySingleton.getInstance();
    }

    public Clasa findClasaById(int id) {
        Session session = sessionFactory.openSession();

        Clasa foundClasa = session.find(Clasa.class, id);

        session.close();

        return foundClasa;

    }

    public void deleteClasaById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Clasa clasaById = findClasaById(id);

        if (clasaById == null) {
            System.out.println("Clasa " + id + " not found");
            return;
        }

        session.delete(clasaById);

        transaction.commit();
        session.close();
    }

    public List<Clasa> findAllClase() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query allClase = session.createQuery("select c from Clasa c");

        List<Clasa> list = allClase.getResultList();
        transaction.commit();
        session.close();

        return list;

    }

    public Clasa addClasa(String numeClasa) {
        Clasa clasa;

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            clasa = new Clasa(numeClasa);
            session.persist(clasa);

            transaction.commit();
        }

        return clasa;


    }
}
