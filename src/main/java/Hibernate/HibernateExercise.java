package Hibernate;

import org.hibernate.Session;

class HibernateExercise {
    public static void main(String[] args) {

        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Uzytkownicy o = new Uzytkownicy();
        session.persist(o);
        session.getTransaction().commit();
        session.close();
        Session session1 = SessionManager.getSessionFactory().openSession();
        session1.beginTransaction();
        Uzytkownicy o = session1.find(Uzytkownicy.class, 5);
        session1.getTransaction().commit();
        session1.close();


    }
}
