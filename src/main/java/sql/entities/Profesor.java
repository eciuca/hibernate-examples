package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "profesori")
@PrimaryKeyJoinColumn(name = "idProfesor")
public class Profesor extends Persoana {

//    @Id
//    @Column(name = "idProfesor", unique = true, nullable = false)
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    Integer idProfesor;

    String numeProfesor;

    @ManyToOne
    @JoinColumn(name = "idMateriePredata")
    Materie materie;

    @OneToMany(mappedBy = "profesor", fetch = FetchType.LAZY)
    List<Nota> notaProfesoriList;

    public Profesor(String numeProfesor, Materie materie) {
        this.numeProfesor = numeProfesor;
        this.materie = materie;
    }

    public Profesor(String numeProfesor) {
        this.numeProfesor = numeProfesor;
    }

    public Profesor(){

    }

    @Override
    public String toString() {
        return "Profesori{" +
                "idProfesor=" + id +
                ", numeProfesor='" + numeProfesor + '\'' +
                ", notaProfesoriList='" + notaProfesoriList + '\'' +
                ", materie=" + materie +
                '}';
    }
}
