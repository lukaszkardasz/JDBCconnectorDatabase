package hibernate;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table
class Zawod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_uzytkownika;
    @Column
    private int id_zawodu;
    @Column
    private String nazwa_zawodu;
    @Column
    private int placa;


    @Override
    public String toString() {
        return "Zawod{" +
                "id_uzytkownika=" + id_uzytkownika +
                ", id_zawodu=" + id_zawodu +
                ", nazwa_zawodu='" + nazwa_zawodu + '\'' +
                ", placa=" + placa +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zawod zawod = (Zawod) o;
        return id_uzytkownika == zawod.id_uzytkownika &&
                id_zawodu == zawod.id_zawodu &&
                placa == zawod.placa &&
                Objects.equals(nazwa_zawodu, zawod.nazwa_zawodu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_uzytkownika, id_zawodu, nazwa_zawodu, placa);
    }

    public int getId_uzytkownika() {
        return id_uzytkownika;
    }

    public void setId_uzytkownika(int id_uzytkownika) {
        this.id_uzytkownika = id_uzytkownika;
    }

    public int getId_zawodu() {
        return id_zawodu;
    }

    public void setId_zawodu(int id_zawodu) {
        this.id_zawodu = id_zawodu;
    }

    public String getNazwa_zawodu() {
        return nazwa_zawodu;
    }

    public void setNazwa_zawodu(String nazwa_zawodu) {
        this.nazwa_zawodu = nazwa_zawodu;
    }

    public int getPlaca() {
        return placa;
    }

    public void setPlaca(int placa) {
        this.placa = placa;
    }
}
