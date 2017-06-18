package edu.hm.shareit;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import edu.hm.shareit.persistence.MediaPersistence;
import edu.hm.shareit.persistence.MediaPersistenceImpl;
import edu.hm.shareit.persistence.Persistence;
import edu.hm.shareit.persistence.PersistenceImpl;
import edu.hm.shareit.service.media.MediaService;
import edu.hm.shareit.service.media.MediaServiceImpl;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.jersey.ServiceLocatorProvider;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jvnet.hk2.guice.bridge.api.GuiceBridge;
import org.jvnet.hk2.guice.bridge.api.GuiceIntoHK2Bridge;

import javax.ws.rs.core.Feature;
import javax.ws.rs.core.FeatureContext;

import static org.mockito.Mockito.mock;
/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/16/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */

public class GuiceInjectionTest implements Feature {

    private static final Injector INJECTOR = Guice.createInjector(new AbstractModule() {
        @Override
        protected void configure() {
            bind(Persistence.class).toInstance(mock(PersistenceImpl.class));
            bind(MediaPersistence.class).toInstance(mock(MediaPersistenceImpl.class));
            bind(MediaService.class).toInstance(mock(MediaServiceImpl.class));
            bind(SessionFactory.class).toInstance(
                    new Configuration()
                            .configure()
                            .setProperty("hibernate.connection.url", "jdbc:hsqldb:mem:shareit-test-db")
                            .buildSessionFactory());
        }
    });

    @Override
    public boolean configure(FeatureContext context) {
        ServiceLocator serviceLocator = ServiceLocatorProvider.getServiceLocator(context);
        GuiceBridge.getGuiceBridge().initializeGuiceBridge(serviceLocator);
        GuiceIntoHK2Bridge guiceBridge = serviceLocator.getService(GuiceIntoHK2Bridge.class);
        guiceBridge.bridgeGuiceInjector(getInjectorInstance());
        return true;
    }

    /**
     * This method is only required for the HK2-Guice-Bridge in the Application class.
     * @return Injector instance.
     */
    public static Injector getInjectorInstance() {
        return INJECTOR;
    }

}

/*
    protected void configure() {
        bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());

        bind(Persistence.class).toInstance(mock(PersistenceImpl.class));
        bind(MediaPersistence.class).toInstance(mock(MediaPersistenceImpl.class));
        bind(MediaService.class).toInstance(mock(MediaServiceImpl.class));


    }

*/
