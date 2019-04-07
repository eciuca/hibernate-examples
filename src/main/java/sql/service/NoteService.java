package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import sql.SessionFactorySingleton;
import sql.entities.Elev;
import sql.entities.Materie;
import sql.entities.Nota;

import javax.persistence.Query;
import java.util.List;

public class NoteService {

    private SessionFactory sessionFactory;
    private EleviService eleviService;
    private MaterieService materieService;

    public NoteService() {
        this.sessionFactory = SessionFactorySingleton.getInstance();
        this.eleviService = new EleviService();
        this.materieService = new MaterieService();
    }

    public List<Nota> getNote(String numeElev, String numeMaterie) {
        // Obtinem elevul dupa nume
        // Obtinem materia dupa nume
        // Cautam toate notele in functie de elev si materie
        // returnam rezultatul
        try(Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Elev elev = eleviService.findElevByName(numeElev);
            Materie materie = materieService.findMaterieByName(numeMaterie);

            /**
             * select note.* from note
             * inner join elevi on note.idElev = elevi.idElev
             * inner join materie on materie.idMaterie = note.idMaterie
             * where elevi.numeElev = 'Cristi'
             * and materie.numeMaterie = 'Matematica';
             */
//            Query query = session.createQuery(
//                    "SELECT n FROM Nota n " +
//                            "JOIN n.elev e " +
//                            "JOIN n.materie m " +
//                            "WHERE e.numeElev = :numeleElevului " +
//                            "AND m.numeMaterie = :numeleMateriei");

            Query query = session.createNamedQuery(Nota.TOATE_NOTELE_UNUI_ELEV_LA_O_MATERIE);
            query.setParameter("numeleElevului", elev.getNumeElev());
            query.setParameter("numeleMateriei", materie.getNumeMaterie());

            List<Nota> listaNote = query.getResultList();

            transaction.commit();

            return listaNote;
        }
    }
}
