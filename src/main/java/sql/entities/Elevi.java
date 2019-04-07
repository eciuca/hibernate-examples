package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "elevi")
public class Elevi {

    @Id
    @Column(name = "idElev", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Integer idElev;

    String numeElev;

    @ManyToOne
    @JoinColumn(name = "idClasa")
    Clasa clasa;

    @OneToMany(mappedBy = "elevi")
    List<Note> noteEleviList;

    public Elevi(String numeElev, Clasa clasa) {
        this.numeElev = numeElev;
        this.clasa = clasa;
    }

    public Elevi(String numeElev) {
        this.numeElev = numeElev;
    }

    public Elevi() {

    }

    @Override
    public String toString() {
        return "Elevi{" +
                "idElev=" + idElev +
                ", numeElev='" + numeElev + '\'' +
                ", clasa=" + clasa +
                '}';
    }
}
