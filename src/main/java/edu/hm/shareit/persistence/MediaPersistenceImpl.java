package edu.hm.shareit.persistence;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;

import java.util.ArrayList;
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

public class MediaPersistenceImpl implements MediaPersistence {
    //@Inject
    private Persistence persist = new PersistenceImpl();

    @Override
    public boolean existBook(String id) {
        return persist.exist(edu.hm.shareit.persistence.media.Book.class, id);
    }

    @Override
    public boolean existDisc(String id) {
        return persist.exist(edu.hm.shareit.persistence.media.Disc.class, id);

    }

    @Override
    public Book getBook(String id) {
        edu.hm.shareit.persistence.media.Book element = persist.get(edu.hm.shareit.persistence.media.Book.class, id);

        return  new Book(element.getTitle(), element.getAuthor(), element.getIsbn());
    }

    @Override
    public Disc getDisc(String id) {
        edu.hm.shareit.persistence.media.Disc element = persist.get(edu.hm.shareit.persistence.media.Disc.class, id);
        return new Disc(element.getBarcode(), element.getDirector(), element.getFsk(),element.getTitle());
    }

    @Override
    public void addBook(Book element) {
        edu.hm.shareit.persistence.media.Book book = new edu.hm.shareit.persistence.media.Book();
        book.setIsbn(element.getIsbn());
        book.setAuthor(element.getAuthor());
        book.setTitle(element.getTitle());

        persist.add(book);
    }

    @Override
    public void addDisc(Disc element) {

        edu.hm.shareit.persistence.media.Disc disc = new edu.hm.shareit.persistence.media.Disc();
        disc.setBarcode(element.getBarcode());
        disc.setDirector(element.getDirector());
        disc.setTitle(element.getTitle());
        disc.setFsk(element.getFsk());

        persist.add(disc);

    }

    @Override
    public List<Book> getAllBooks() {
        List<edu.hm.shareit.persistence.media.Book> books = persist.getAll(edu.hm.shareit.persistence.media.Book.class);
        List<Book> result = new ArrayList<>();

        for (edu.hm.shareit.persistence.media.Book element:
             books) {
            result.add(new Book(element.getTitle(), element.getAuthor(), element.getIsbn()));
        }

        return result;
    }

    @Override
    public List<Disc> getAllDiscs() {
        List<edu.hm.shareit.persistence.media.Disc> discs = persist.getAll(edu.hm.shareit.persistence.media.Disc.class);
        List<Disc> result = new ArrayList<>();

        for (edu.hm.shareit.persistence.media.Disc element:
                discs) {
            result.add(new Disc(element.getBarcode(), element.getDirector(), element.getFsk(),element.getTitle()));
        }

        return result;
    }

    @Override
    public void updateBook(Book element) {

        edu.hm.shareit.persistence.media.Book book = new edu.hm.shareit.persistence.media.Book();
        book.setIsbn(element.getIsbn());
        book.setAuthor(element.getAuthor());
        book.setTitle(element.getTitle());

        persist.update(book);
    }

    @Override
    public void updateDisc(Disc element) {
        edu.hm.shareit.persistence.media.Disc disc = new edu.hm.shareit.persistence.media.Disc();
        disc.setBarcode(element.getBarcode());
        disc.setDirector(element.getDirector());
        disc.setTitle(element.getTitle());
        disc.setFsk(element.getFsk());

        persist.update(disc);

    }
}
