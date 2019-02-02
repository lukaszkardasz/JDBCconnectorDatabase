package hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

class ZawodRepositoryHibernate implements ZawodRepository {


    @Override
    public List<Zawod> findAll() {
        Transaction transaction = null; //rozpoczynamy tranzakcję
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            //otwieramy przez sesję nowa tranzakcję
            transaction = session.beginTransaction();
            //zwracanie wszystkiego z tableli zawod
            Query<Zawod> query = session.createQuery("from Zawod", Zawod.class);
            List<Zawod> list = query.list();
            transaction.commit();
            transaction = null;
            return list; //zwracamy listę wszystkich obiektów z tabeli zawod
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //zabezpieczamy się rollbackiem przed tym jakby coś się nie udało z tranzakcją, wtedy przywracamy ostatnie zmiany rollbackiem
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Collections.emptyList(); //jezeli ktoś uzyje naszej metody i tam nic nie będzie to dostałby null pointer eception, ale my tam wsadzimy pustą listę żeby wiedział ze nie ma wyników
    }

    @Override
    public Optional<Zawod> findById(int id) {
        Transaction transaction = null; //rozpoczynamy tranzakcję
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Zawod zawod = session.find(Zawod.class, id);
            transaction.commit();
            transaction = null;
            return Optional.ofNullable(zawod); //stosujemy Optionala, zabezpieczamy się tym przed tym, że nie dostaniemy null pointer exception przy wyszukaniu id ktore nie istnieje,
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //zabezpieczamy się rollbackiem przed tym jakby coś się nie udało z tranzakcją, wtedy przywracamy ostatnie zmiany rollbackiem
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return Optional.empty(); //jesli nie znajdzie takiego id to nie wywali nullpointerexc tylko pustego optionala
    }


    @Override
    public void save(Zawod zawod) {
        Transaction transaction = null; //rozpoczynamy tranzakcję
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            //otwieramy przez sesję nowa tranzakcję
            transaction = session.beginTransaction();
            session.save(zawod);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //zabezpieczamy się rollbackiem przed tym jakby coś się nie udało z tranzakcją, wtedy przywracamy ostatnie zmiany rollbackiem
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void update(Zawod zawod) {
        Transaction transaction = null; //rozpoczynamy tranzakcję
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            //otwieramy przez sesję nowa tranzakcję
            transaction = session.beginTransaction();
            session.update(zawod);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //zabezpieczamy się rollbackiem przed tym jakby coś się nie udało z tranzakcją, wtedy przywracamy ostatnie zmiany rollbackiem
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(Zawod zawod) {
        Transaction transaction = null; //rozpoczynamy tranzakcję
        try (Session session = SessionManager.getSessionFactory().openSession()) {
            //otwieramy przez sesję nowa tranzakcję
            transaction = session.beginTransaction();
            session.delete(zawod);
            transaction.commit();
            transaction = null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally { //zabezpieczamy się rollbackiem przed tym jakby coś się nie udało z tranzakcją, wtedy przywracamy ostatnie zmiany rollbackiem
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }
}
