package sql;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import sql.entities.*;

import javax.persistence.Query;


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

        Elev elev = new Elev("CelMaiBun La Chimie", clasa);
        elev.setCnp("191022322222");
        session.persist(elev);

        Materie materie = new Materie("Chimie");
        session.persist(materie);

        Profesor prof = new Profesor("Mendeleev");
        prof.setCnp("291022322222");
        Adresa a = new Adresa();
        a.setBloc("R1");
        a.setScara("B");
        a.setStrada("Drm Malu Mierii");
        prof.setAdresa(a);
        session.persist(prof);



        Nota nota = new Nota("10", prof, materie, elev);
        session.persist(nota);

        System.out.println(nota);



       // Elevi elevi = new Elevi("Gigel2", clasa3);
       // session.persist(elevi);

       // System.out.println(elevi);

        transaction.commit();
        session.close();
    }
}
