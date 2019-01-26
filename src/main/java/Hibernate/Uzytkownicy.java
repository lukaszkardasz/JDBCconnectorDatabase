package Hibernate;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
class Uzytkownicy {

        @Id
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

    }
