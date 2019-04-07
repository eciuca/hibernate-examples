package sql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import sql.entities.*;

public class Hibernate {

    public static void main(String[] args) {


        // Create registry
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        // Create MetadataSources
        MetadataSources sources = new MetadataSources(registry);
        // Create Metadata
        Metadata metadata = sources.getMetadataBuilder().build();
        // Create SessionFactory
        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();


        Session session = sessionFactory.openSession();
        Transaction transaction =  session.beginTransaction();

        // Profesori p = session.find(Profesori.class, 3);
        // System.out.println(p);
        // System.out.println(p.numeProfesor);
        // System.out.println(p.materie);

        Clasa clasa = new Clasa("11C");
        session.persist(clasa);

        Elevi elevi = new Elevi("CelMaiBun La Chimie", clasa);
        session.persist(elevi);

        Materie materie = new Materie("Chimie");
        session.persist(materie);

        Profesori prof = new Profesori("Mendeleev");
        session.persist(prof);



        Note note = new Note("10", prof, materie,elevi);
        session.persist(note);

        System.out.println(note);



       // Elevi elevi = new Elevi("Gigel2", clasa3);
       // session.persist(elevi);

       // System.out.println(elevi);





        transaction.commit();
        session.close();
    }
}
