package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profesori")
public class Profesori {

    @Id
    @Column(name = "idProfesor", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Integer idProfesor;

    String numeProfesor;

    @ManyToOne
    @JoinColumn(name = "idMateriePredata")
    Materie materie;

    @OneToMany(mappedBy = "profesori")
    List<Note> noteProfesoriList;

    public Profesori(String numeProfesor, Materie materie) {
        this.numeProfesor = numeProfesor;
        this.materie = materie;
    }

    public Profesori(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public Profesori(){

    }

    @Override
    public String toString() {
        return "Profesori{" +
                "idProfesor=" + idProfesor +
                ", numeProfesor='" + numeProfesor + '\'' +
                ", materie=" + materie +
                '}';
    }
}
