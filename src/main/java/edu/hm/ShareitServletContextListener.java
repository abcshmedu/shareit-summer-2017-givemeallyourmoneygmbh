package edu.hm;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/13/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import edu.hm.shareit.persistence.MediaPersistence;
import edu.hm.shareit.persistence.MediaPersistenceImpl;
import edu.hm.shareit.persistence.Persistence;
import edu.hm.shareit.persistence.PersistenceImpl;
import edu.hm.shareit.service.media.MediaService;
import edu.hm.shareit.service.media.MediaServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Context Listener to enable usage of google guice together with jersey.
 */


public class ShareitServletContextListener extends GuiceServletContextListener {



    private static final Injector injector = Guice.createInjector(new ServletModule() {
        @Override
        protected void configureServlets() {
            bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());

            bind(Persistence.class).to(PersistenceImpl.class);
            bind(MediaPersistence.class).to(MediaPersistenceImpl.class);
            bind(MediaService.class).to(MediaServiceImpl.class);
            //bind(AuthenticationService.class).to(AuthenticationServiceImpl.class);

            //bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
            //bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
            //bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
            //bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
        }
    });

    @Override
    public Injector getInjector() {
        return injector;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the
     * Application class.
     * @return Injector instance.
     */

    static Injector getInjectorInstance() {
        return injector;
    }
}