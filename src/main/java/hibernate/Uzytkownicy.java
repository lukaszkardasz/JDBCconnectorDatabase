package hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "uzytkownicy")
@NamedQueries({
        @NamedQuery(name = "selectByName",
        query = "select s from Uzytkownicy s where s.IMIE = :IMIE") //podajemy query, które kompuluje się tylko raz i można go używać wielokrotnie przez nazwe name
        })

class Uzytkownicy {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY )
        @Column
        private int ID;
        @Column
        private String IMIE;
        @Column
        private String NAZWISKO;
        @Column
        private String MIASTO;
        @Column
        private String ULICA;
        @Column
        private String KOD_POCZTOWY;
        @Column
        private int NR_DOMU;
        @Column
        private String DATA_URODZENIA;
        @Column
        private int waga;
        @Column
        private int wzrost;
        @Column
        private String KRAJ;
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "id_uzytkownika")
        private Zawod zawod;


    @Override
    public String toString() {
        return "Uzytkownicy{" +
                "ID=" + ID +
                ", IMIE='" + IMIE + '\'' +
                ", NAZWISKO='" + NAZWISKO + '\'' +
                ", MIASTO='" + MIASTO + '\'' +
                ", ULICA='" + ULICA + '\'' +
                ", KOD_POCZTOWY='" + KOD_POCZTOWY + '\'' +
                ", NR_DOMU=" + NR_DOMU +
                ", DATA_URODZENIA='" + DATA_URODZENIA + '\'' +
                ", waga=" + waga +
                ", wzrost=" + wzrost +
                ", KRAJ='" + KRAJ + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzytkownicy that = (Uzytkownicy) o;
        return ID == that.ID &&
                NR_DOMU == that.NR_DOMU &&
                waga == that.waga &&
                wzrost == that.wzrost &&
                Objects.equals(IMIE, that.IMIE) &&
                Objects.equals(NAZWISKO, that.NAZWISKO) &&
                Objects.equals(MIASTO, that.MIASTO) &&
                Objects.equals(ULICA, that.ULICA) &&
                Objects.equals(KOD_POCZTOWY, that.KOD_POCZTOWY) &&
                Objects.equals(DATA_URODZENIA, that.DATA_URODZENIA) &&
                Objects.equals(KRAJ, that.KRAJ);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, IMIE, NAZWISKO, MIASTO, ULICA, KOD_POCZTOWY, NR_DOMU, DATA_URODZENIA, waga, wzrost, KRAJ);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getIMIE() {
        return IMIE;
    }

    public void setIMIE(String IMIE) {
        this.IMIE = IMIE;
    }

    public String getNAZWISKO() {
        return NAZWISKO;
    }

    public void setNAZWISKO(String NAZWISKO) {
        this.NAZWISKO = NAZWISKO;
    }

    public String getMIASTO() {
        return MIASTO;
    }

    public void setMIASTO(String MIASTO) {
        this.MIASTO = MIASTO;
    }

    public String getULICA() {
        return ULICA;
    }

    public void setULICA(String ULICA) {
        this.ULICA = ULICA;
    }

    public String getKOD_POCZTOWY() {
        return KOD_POCZTOWY;
    }

    public void setKOD_POCZTOWY(String KOD_POCZTOWY) {
        this.KOD_POCZTOWY = KOD_POCZTOWY;
    }

    public int getNR_DOMU() {
        return NR_DOMU;
    }

    public void setNR_DOMU(int NR_DOMU) {
        this.NR_DOMU = NR_DOMU;
    }

    public String getDATA_URODZENIA() {
        return DATA_URODZENIA;
    }

    public void setDATA_URODZENIA(String DATA_URODZENIA) {
        this.DATA_URODZENIA = DATA_URODZENIA;
    }

    public int getWaga() {
        return waga;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public int getWzrost() {
        return wzrost;
    }

    public void setWzrost(int wzrost) {
        this.wzrost = wzrost;
    }

    public String getKRAJ() {
        return KRAJ;
    }

    public void setKRAJ(String KRAJ) {
        this.KRAJ = KRAJ;
    }
}
