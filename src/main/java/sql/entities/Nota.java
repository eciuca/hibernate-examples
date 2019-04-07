package sql.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "note")
@NamedQueries({
        @NamedQuery(name = Nota.TOATE_NOTELE_UNUI_ELEV_LA_O_MATERIE,
        query = "SELECT n FROM Nota n " +
                "JOIN n.elev e " +
                "JOIN n.materie m " +
                "WHERE e.numeElev = :numeleElevului " +
                "AND m.numeMaterie = :numeleMateriei"),
        @NamedQuery(name = "toate_notele", query = "SELECT n FROM Nota n")
})
public class Nota {

    public static final String TOATE_NOTELE_UNUI_ELEV_LA_O_MATERIE = "toate_notele_unui_elev_la_o_materie";

    @Id
    @Column(name = "idNote", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    Integer idNote;
    String valoareNota;

    @ManyToOne
    @JoinColumn(name = "idProfesor")
    Profesor profesor;

    @ManyToOne
    @JoinColumn(name = "idMaterie")
    Materie materie;

    @ManyToOne
    @JoinColumn(name = "idElev")
    Elev elev;

    public Nota() {
    }

    public Nota(String valoareNota, Profesor profesor, Materie materie, Elev elev) {
        this.valoareNota = valoareNota;
        this.profesor = profesor;
        this.materie = materie;
        this.elev = elev;
    }

    @Override
    public String toString() {
        return "Note{" +
                "idNote=" + idNote +
                ", valoareNota='" + valoareNota + '\'' +
                ", profesori=" + profesor +
                ", materie=" + materie +
                ", elevi=" + elev.getNumeElev() +
                '}';
    }
}
