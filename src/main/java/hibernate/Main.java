package hibernate;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

class Main {
    public static void main(String[] args) {
        //addAndSave();
        //find(5);
        //update(7);
        //delete(16);
        findAndShowByName("Zdzichu");
        findByNamedQuery("Wiesiek");



        //zamykanie samoczynne aplikacji
        SessionManager.getSessionFactory().close();

    }

    private static void findByNamedQuery(String name) {
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        session.createNamedQuery("selectByName", Uzytkownicy.class);
        session.getTransaction().commit();
        session.close();
    }

    private static void findAndShowByName(String name) {

        Session session5 = SessionManager.getSessionFactory().openSession();
        session5.beginTransaction();
        String hqlQuery = "select s from Uzytkownicy s where s.IMIE = :IMIE";
        Query<Uzytkownicy> query = session5.createQuery(hqlQuery, Uzytkownicy.class);
        query.setParameter("IMIE", name);
        List<Uzytkownicy> list = query.list();

        for(Uzytkownicy record : list){
            System.out.println(record);
        }

        session5.getTransaction().commit();
        session5.close();

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
