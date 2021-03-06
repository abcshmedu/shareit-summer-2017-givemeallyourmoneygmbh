package edu.hm.shareit.persistence;

import com.google.inject.AbstractModule;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/16/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */

public class GuiceModule extends AbstractModule {

    protected void configure() {
        bind(SessionFactory.class).toInstance(new Configuration().configure().buildSessionFactory());
    }

}
