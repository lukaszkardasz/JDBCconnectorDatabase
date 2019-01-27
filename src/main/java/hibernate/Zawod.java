package hibernate;


import javax.persistence.*;

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
}
