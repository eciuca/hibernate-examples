package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "materie")
public class Materie {

    @Id
    @Column(name = "idMaterie", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Integer idMaterie;

    String numeMaterie;

    @OneToMany(mappedBy = "materie", fetch = FetchType.LAZY)
    List<Profesor> profesorList;

    @OneToMany(mappedBy = "materie", fetch = FetchType.LAZY)
    List<Nota> notaMaterieList;

    public Materie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
    }

    public Materie() {

    }

    public Integer getIdMaterie() {
        return idMaterie;
    }

    public void setIdMaterie(Integer idMaterie) {
        this.idMaterie = idMaterie;
    }

    public String getNumeMaterie() {
        return numeMaterie;
    }

    public void setNumeMaterie(String numeMaterie) {
        this.numeMaterie = numeMaterie;
    }

    public List<Profesor> getProfesorList() {
        return profesorList;
    }

    public void setProfesorList(List<Profesor> profesorList) {
        this.profesorList = profesorList;
    }

    public List<Nota> getNotaMaterieList() {
        return notaMaterieList;
    }

    public void setNotaMaterieList(List<Nota> notaMaterieList) {
        this.notaMaterieList = notaMaterieList;
    }

    @Override
    public String toString() {
        return "Materie{" +
                "idMaterie=" + idMaterie +
                ", numeMaterie='" + numeMaterie + '\'' +
                '}';
    }
}
