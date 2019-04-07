package sql.entities;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "persoane")
@Inheritance(strategy = InheritanceType.JOINED)
public class Persoana {

    @Id
    @Column(name = "idPersoana", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    protected Integer id;

    String cnp;

    @Embedded
    private Adresa adresa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Adresa getAdresa() {
        return adresa;
    }

    public void setAdresa(Adresa adresa) {
        this.adresa = adresa;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Persoana persoana = (Persoana) o;
        return Objects.equals(id, persoana.id) &&
                Objects.equals(cnp, persoana.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cnp);
    }

    @Override
    public String toString() {
        return "Persoana{" +
                "id=" + id +
                ", cnp='" + cnp + '\'' +
                '}';
    }
}
