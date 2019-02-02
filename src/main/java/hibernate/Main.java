package hibernate;

import org.hibernate.Session;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.hibernate.envers.RevisionType;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQueryCreator;
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
        //update(9);
        //delete(16);
        //findAndShowByName("Zdzichu");
        //findByNamedQuery("Franek"); //sluzy tylko do selectów, nie mozna użyc jako named query
        //updateByName("Bodzio", "Janusz");
        //deleteQuery(15);
        //selectByCriteria();


        //zeby dzialalo w hibernate cfg trzeba zmienić na update
        //AuditWithUpdateNazwaZawodu();
        AuditReaderWithUpdate();



        //List<Zawod> all = repository.findAll();
        //System.out.println(all);



        //zamykanie samoczynne sesji a tym samym programu, że nie trzeba tego robic recznie
        SessionManager.getSessionFactory().close();

    }

    private static void AuditReaderWithUpdate() {
        Session session = SessionManager.getSessionFactory().openSession();
        AuditReader auditReader = AuditReaderFactory
                .get(session);

        Zawod zawod = auditReader.find(Zawod.class, 1, 3);
        System.out.println(zawod);
        AuditQueryCreator auditQueryCreator = auditReader.createQuery();
        List<Object> resultList = auditQueryCreator.forRevisionsOfEntity(
                Zawod.class,
                true,
                true)
                .add(AuditEntity.property("nazwa_zawodu").eq("Robol"))
                .add(AuditEntity.id().eq(1)) //tylko z id = 1 w id_uzytkownika
                .add(AuditEntity.revisionType().eq(RevisionType.MOD)) //ADD - to dodane za pierwszym razem, MOD zmodyfikowane tylko szuka, DEL - usunięte
                .getResultList();
        for (Object object : resultList) {
            Zawod zawod1 = (Zawod) object;
            System.out.println(zawod);
        }
    }

    private static void AuditWithUpdateNazwaZawodu() {
        ZawodRepository repository = new ZawodRepositoryHibernate();
        Optional<Zawod> byId = repository.findById(4);
        if (byId.isPresent()){
            Zawod zawod = byId.get();
            System.out.println(zawod);
            zawod.setNazwa_zawodu("Taksówkarz"); //mozna tu uzyc kazdej metody set name get id czy co tam mamy w intefejsie
            repository.update(zawod);
        }

        Session session = SessionManager.getSessionFactory().openSession();
        AuditReader auditReader = AuditReaderFactory.get(session);
        Zawod zawod = auditReader.find(Zawod.class, 1, 3);
        System.out.println(zawod);
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
