package hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

class Main {
    public static void main(String[] args) {
        //addAndSave();
        //find(5);
        //update(7);
        //delete(16);
        //findAndShowByName("Zdzichu");
        //findByNamedQuery("Franek"); //sluzy tylko do selectów, nie mozna użyc jako named query
        //updateByName("Bodzio", "Janusz");
        //deleteQuery(15);
        //selectByCriteria();

        ZawodRepository repository = new ZawodRepositoryHibernate();

        //List<Zawod> all = repository.findAll();
        //System.out.println(all);

        Optional<Zawod> byId = repository.findById(2);
        if (byId.isPresent()){
            Zawod zawod = byId.get();
            System.out.println(zawod);
        }

        //zamykanie samoczynne aplikacji
        SessionManager.getSessionFactory().close();

    }

    private static void selectByCriteria() {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
    //select customerRoot
    // from Customer customerRoot
    //where customerRoot.name = Franek
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
    //typ zwracany
        CriteriaQuery<Uzytkownicy> criteriaQuery = criteriaBuilder.createQuery(Uzytkownicy.class);
    //inicjalizacja from
        Root<Uzytkownicy> uzytkownicyRoot = criteriaQuery.from(Uzytkownicy.class);
    //blok where
        criteriaQuery.where(
                criteriaBuilder.equal(uzytkownicyRoot.get("IMIE"),"Franek"));
        Query<Uzytkownicy> query = session.createQuery(criteriaQuery);
        List<Uzytkownicy> list = query.list();
        System.out.println("Wyswietlam listę: " + list);
        session.getTransaction().commit();
        session.close();
    }

    private static void deleteQuery(int idToDelete) {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("delete Uzytkownicy where id in(:idsForDelete)");
        query.setParameter("idsForDelete", idToDelete);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private static void updateByName(String name, String oldName) {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        String hqlQuery = "update Uzytkownicy set IMIE = :newName where IMIE = :oldName";
        Query<Uzytkownicy> query = session.createQuery(hqlQuery);
        query.setParameter("newName", name);
        query.setParameter("oldName", oldName);
        System.out.println("Update " + oldName + " to " + name + " complete");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    private static void findByNamedQuery(String name) {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Query<Uzytkownicy> query = session
                .createNamedQuery("selectByName", Uzytkownicy.class);
        query.setParameter("IMIE", name);
        List<Uzytkownicy> list = query.list();
        for (Uzytkownicy record : list) {
            System.out.println(record);
        }
        session.getTransaction().commit();
        session.close();
    }

    private static void findAndShowByName(String name) {

        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        String hqlQuery = "select s from Uzytkownicy s where s.IMIE = :IMIE";
        Query<Uzytkownicy> query = session.createQuery(hqlQuery, Uzytkownicy.class);
        query.setParameter("IMIE", name);
        List<Uzytkownicy> list = query.list();

        for (Uzytkownicy record : list) {
            System.out.println(record);
        }

        session.getTransaction().commit();
        session.close();

    }

    private static void delete(int userId) {
        Session session4 = SessionManager.getSessionFactory().openSession();
        session4.beginTransaction();
        Uzytkownicy user = session4.find(Uzytkownicy.class, userId);
        session4.delete(user);
        session4.getTransaction().commit();
        session4.close();
    }

    private static void update(int userId) {
        Session session3 = SessionManager.getSessionFactory().openSession();
        session3.beginTransaction();
        Uzytkownicy user = session3.find(Uzytkownicy.class, userId);
        user.setIMIE("NoweImie");
        session3.persist(user); //nie potrzebne w sumie
        session3.getTransaction().commit();
        session3.close();
    }

    private static void find(int userId) {
        Session session1 = SessionManager.getSessionFactory().openSession();
        session1.beginTransaction();
        Uzytkownicy userNo5 = session1.find(Uzytkownicy.class, userId);
        doSomethingWithData(userNo5);
        session1.getTransaction().commit();
        session1.close();
    }

    private static void addAndSave() {
        Session session2 = SessionManager.getSessionFactory().openSession();
        session2.beginTransaction();
        Uzytkownicy user = new Uzytkownicy();
        user.setIMIE("Zdzichu");
        user.setNAZWISKO("Gruszka");
        user.setDATA_URODZENIA("1987-09-08");
        user.setKOD_POCZTOWY("12-098");
        user.setKRAJ("USA");
        user.setMIASTO("Poznań");
        user.setNR_DOMU(98);
        user.setULICA("Barburki");
        user.setWaga(88);
        user.setWzrost(187);
        session2.persist(user);
        session2.getTransaction().commit();
        session2.close();
    }

    private static void doSomethingWithData(Uzytkownicy user) {
        System.out.println(user.getID());
        System.out.println(user.getIMIE());
        System.out.println("________________");
        System.out.println(user);
    }
}
