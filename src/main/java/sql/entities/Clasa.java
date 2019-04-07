package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "clasa")
public class Clasa {

    @Id
    @Column(name = "idClasa", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Integer idClasa;

    String numeClasa;

    @OneToMany(mappedBy = "clasa")
    List<Elevi> eleviList;

    @Override
    public String toString() {
        return "Clasa{" + " idClasa= " + idClasa + ", numeClasa=" + numeClasa + " }";

    }

    public Clasa() {

    }

    public Clasa(String numeClasa) {
        this.numeClasa = numeClasa;
    }
}

