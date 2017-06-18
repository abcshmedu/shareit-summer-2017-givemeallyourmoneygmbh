package edu.hm.shareit.persistence;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;

import java.util.List;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/14/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */

public interface MediaPersistence {

    boolean existBook(String id);
    boolean existDisc(String id);


    Book getBook(String id);
    Disc getDisc(String id);


    void addBook(Book element);
    void addDisc(Disc element);

    List<Book> getAllBooks();
    List<Disc> getAllDiscs();

    void  updateBook(Book element);
    void updateDisc(Disc element);

}