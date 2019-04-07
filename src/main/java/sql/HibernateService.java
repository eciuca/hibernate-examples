package sql;

import sql.entities.Clasa;
import sql.entities.Elevi;
import sql.service.ClasaService;
import sql.service.EleviService;

import java.util.List;

public class HibernateService {

    public static void main(String[] args) {
        ClasaService clasaService = new ClasaService();
        EleviService eleviService = new EleviService();

        Clasa clasaById = clasaService.findClasaById(2);
        System.out.println(clasaById);

        Elevi elevById = eleviService.findElevById(3);
        System.out.println(elevById);

        List<Elevi> allElevi = eleviService.findAllElevi();
        System.out.println(allElevi);

        Elevi elevByName = eleviService.findElevByName("Catalina");
        System.out.println(elevByName);

    }


}
