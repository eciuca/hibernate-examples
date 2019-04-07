package sql.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import sql.SessionFactorySingleton;
import sql.entities.Clasa;
import sql.entities.Elev;

import java.util.*;
import java.util.stream.Collectors;

public class EleviService {

    private SessionFactory sessionFactory;
    private ClasaService clasaService;

    public EleviService() {
        this.clasaService = new ClasaService();
        this.sessionFactory = SessionFactorySingleton.getInstance();
    }

    public Elev findElevById(int id) {
        Elev foundElev;
        try (Session session = sessionFactory.openSession()) {

            foundElev = session.find(Elev.class, id);

            session.close();
        }

        return foundElev;
    }

    public void deleteElevById(int id) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Elev elevById = findElevById(id);

        if (elevById == null) {
            System.out.println("Elev " + id + " not found");
            return;
        }

        session.delete(elevById);

        transaction.commit();
        session.close();
    }

    public Elev addElev(String numeElev, int idClasa) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Clasa clasa = clasaService.findClasaById(idClasa);

        Elev elev = new Elev(numeElev, clasa);
        session.persist(elev);

        transaction.commit();
        session.close();

        return elev;
    }

    public List<Elev> findAllElevi() {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query allElevi = session.createQuery("select e from Elev e");

        List<Elev> list = allElevi.getResultList();
        transaction.commit();
        session.close();

        return list;

    }

    public Elev findElevByName(String name) {

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Query specificElevQuery = session.createQuery("select e from Elev e where e.numeElev = :name");

        specificElevQuery.setParameter("name", name);

        Elev elevToBeReturned = (Elev) specificElevQuery.uniqueResult();
        transaction.commit();
        session.close();

        return elevToBeReturned;

    }

    public List<Elev> filtreazaEleviDupa(String numeElev, String numeClasa, String valoareNota) {
        try (Session session = sessionFactory.openSession()) {

            List<String> joinClauses = new ArrayList<>();
            List<String> whereClauses = new ArrayList<>();
            Map<String, Object> parameters = new HashMap<>();
//            String s ="SELECT e FROM Elev e " +
//                    "JOIN e.clasa c " +
//                    "WHERE
//                    "e.numeElev = :numeleElevului " +
//                    "AND c.numeClasa = :numeleClasei " +
//                    "AND e.notaEleviList in :listaNote";
            String query = "SELECT e FROM Elev e";

            if (numeElev != null && !numeElev.trim().isEmpty()) {
                whereClauses.add("e.numeElev = :numeleElevului");
                parameters.put("numeleElevului", numeElev);
            }

            if (numeClasa != null && !numeClasa.trim().isEmpty()) {
                joinClauses.add("JOIN e.clasa c");
                whereClauses.add("c.numeClasa = :numeleClasei");
                parameters.put("numeleClasei", numeClasa);
            }

            if (valoareNota != null && !valoareNota.trim().isEmpty()) {
                joinClauses.add("join e.notaEleviList ne");
                whereClauses.add("ne.valoareNota in :listaNote");
                parameters.put("listaNote", Arrays.asList(valoareNota));
            }

            if (!joinClauses.isEmpty()) {
                query += " " + String.join(" ", joinClauses);
            }

            if (!whereClauses.isEmpty()) {
                query += " WHERE " + String.join(" AND ", whereClauses);
            }

            Query queryObj = session.createQuery(query);

            for (String paramName : parameters.keySet()) {
                queryObj.setParameter(paramName, parameters.get(paramName));
            }

            List<Elev> resultList = queryObj.getResultList();

//            resultList.get(0).getNotaEleviList().forEach(System.out::println);

            return resultList;
        }
    }
}
