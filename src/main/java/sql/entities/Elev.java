package sql.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "elevi")
@PrimaryKeyJoinColumn(name = "idElev")
public class Elev extends Persoana {

//    @Id
//    @Column(name = "idElev", unique = true, nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    Integer idElev;

    String numeElev;

    @ManyToOne
    @JoinColumn(name = "idClasa")
    Clasa clasa;

    @OneToMany(mappedBy = "elev", fetch = FetchType.LAZY)
    List<Nota> notaEleviList;

    public Elev(String numeElev, Clasa clasa) {
        this.numeElev = numeElev;
        this.clasa = clasa;
    }

    public Elev(String numeElev) {
        this.numeElev = numeElev;
    }

    public Elev() {

    }

    public Integer getIdElev() {
        return id;
    }

    public void setIdElev(Integer idElev) {
        this.id = idElev;
    }

    public String getNumeElev() {
        return numeElev;
    }

    public void setNumeElev(String numeElev) {
        this.numeElev = numeElev;
    }

    public Clasa getClasa() {
        return clasa;
    }

    public void setClasa(Clasa clasa) {
        this.clasa = clasa;
    }

    public List<Nota> getNotaEleviList() {
        return notaEleviList;
    }

    public void setNotaEleviList(List<Nota> notaEleviList) {
        this.notaEleviList = notaEleviList;
    }

    @Override
    public String toString() {
        return "Elevi{" +
                "idElev=" + id +
                ", numeElev='" + numeElev + '\'' +
                ", clasa=" + clasa +
//                ", note=" + Arrays.toString(notaEleviList.toArray()) +
                '}';
    }
}
