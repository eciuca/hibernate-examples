package sql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import sql.entities.Persoana;

import javax.persistence.Query;

public class Aplicatie3 {

    public static void main(String[] args) {
        SessionFactory instance = SessionFactorySingleton.getInstance();
        Session session = instance.openSession();


        Query query = session.createQuery("SELECT p FROM Persoana p");

        query.getResultList().forEach(p ->{
            System.out.println(p);
            System.out.println(((Persoana) p).getAdresa());
        });

        session.close();
    }
}
