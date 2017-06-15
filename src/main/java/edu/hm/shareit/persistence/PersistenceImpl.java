package edu.hm.shareit.persistence;

import com.google.inject.Inject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.Serializable;
import java.util.List;

//import javax.inject.Inject;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/14/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */

/*
class GuiceModule extends AbstractModule {

    protected void configure() {
        bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
    }

}
*/

public class PersistenceImpl implements Persistence{

    //private static final Injector injector = ;
    @Inject
    private SessionFactory sessionFactory ;
    //@Inject
    //private SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
    //@Inject
    //private SessionFactory sessionFactory;

    /*private static final Injector injector = Guice.createInjector(new GuiceModule());
    @Inject
    private SessionFactory sessionFactory ;

    public PersistenceImpl() {
        injector.injectMembers(this);
    }*/

    @Override
    public <T, K  extends Serializable> boolean exist(Class<T> tClass, K key) {

        try( Session entityManager = sessionFactory.getCurrentSession()){
            Transaction transaction = entityManager.beginTransaction();
            T element = entityManager.get(tClass, key);
            transaction.commit();

            return element != null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public <T, K extends Serializable> T get(Class<T> tClass, K key) {
     try( Session entityManager = sessionFactory.getCurrentSession()){
            Transaction transaction = entityManager.beginTransaction();
            T element = entityManager.get(tClass, key);
            transaction.commit();

            return element;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> void add(T element) {

        Transaction transaction = null;

        try( Session entityManager = sessionFactory.getCurrentSession()){
            transaction = entityManager.beginTransaction();
            entityManager.save(element);
            transaction.commit();

        }
        catch (Exception e) {
            if(transaction != null)
                transaction.rollback();
            e.printStackTrace();
        }


    }

    @Override
    public <T> List<T> getAll(Class<T> tClass) {

        Transaction transaction = null;
        try( Session entityManager = sessionFactory.getCurrentSession()){
            transaction = entityManager.beginTransaction();

            String queryString = "from " + tClass.getSimpleName();
            List<T> list = entityManager.createQuery(queryString).list();

            transaction.commit();
            return list;

        }
        catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public <T> void update(T elemement) {
        Transaction transaction = null;

        try( Session entityManager = sessionFactory.getCurrentSession()){
            transaction = entityManager.beginTransaction();
            entityManager.saveOrUpdate(elemement);
            transaction.commit();

        }
        catch (Exception e) {

            e.printStackTrace();
        }

    }
}
