package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sql.SessionFactorySingleton;
import sql.entities.Clasa;
import sql.entities.Elevi;

import java.util.List;

public class EleviService {

    private SessionFactory sessionFactory;
    private ClasaService clasaService;

    public EleviService() {
        this.clasaService = new ClasaService();
        this.sessionFactory = SessionFactorySingleton.getInstance();
    }

    public Elevi findElevById(int id) {
        Elevi foundElev;
        try (Session session = sessionFactory.openSession()) {

            foundElev = session.find(Elevi.class, id);

            session.close();
        }

        return foundElev;
    }

    public void deleteElevById(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Elevi elevById = findElevById(id);

        if (elevById == null) {
            System.out.println("Elev " + id + " not found");
            return;
        }

        session.delete(elevById);

        transaction.commit();
        session.close();
    }

    public Elevi addElev(String numeElev, int idClasa) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Clasa clasa = clasaService.findClasaById(idClasa);

        Elevi elevi = new Elevi(numeElev, clasa);
        session.persist(elevi);

        transaction.commit();
        session.close();

        return elevi;
    }

    public List<Elevi> findAllElevi() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query allElevi = session.createQuery("select e from Elevi e");

        List<Elevi> list = allElevi.getResultList();
        transaction.commit();
        session.close();

        return list;

    }

    public Elevi findElevByName(String name) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query specificElevQuery = session.createQuery("select e from Elevi e " + "where e.numeElev =  :name");

        specificElevQuery.setParameter("name", name);

        Elevi eleviToBeReturned = (Elevi) specificElevQuery.uniqueResult();
        transaction.commit();
        session.close();

        return eleviToBeReturned;

    }

}
