package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "note")

public class Note {

    @Id
    @Column(name = "idNote", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Integer idNote;
    String valoareNota;

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    Profesori profesori;

    @ManyToOne
    @JoinColumn(name = "idMaterie")
    Materie materie;

    @ManyToOne
    @JoinColumn(name = "idElev")
    Elevi elevi;

    public Note() {
    }

    public Note(String valoareNota, Profesori profesori, Materie materie, Elevi elevi) {
        this.valoareNota = valoareNota;
        this.profesori = profesori;
        this.materie = materie;
        this.elevi = elevi;
    }

    @Override
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", valoareNota='" + valoareNota + '\'' +
                ", profesori=" + profesori +
                ", materie=" + materie +
                ", elevi=" + elevi +
                '}';
    }
}
