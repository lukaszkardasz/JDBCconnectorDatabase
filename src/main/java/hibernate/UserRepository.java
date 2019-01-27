package hibernate;

import org.hibernate.Session;

class UserRepository {

    public void createUserWithJob(){
        Session session = SessionManager.getSessionFactory().openSession();
        session.beginTransaction();
        Uzytkownicy user = new Uzytkownicy();
        user.setIMIE("Władysław");
        user.setNAZWISKO("Gąbka");
    }
}
