package edu.hm.shareit.service.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;
import edu.hm.shareit.model.media.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * MediaService implementation.
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 4/22/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public class MediaServiceImpl implements MediaService {


    //in der naechsten Aufgabe auslagern nach Persistence Layer
    private Map<String, Medium> mediaMap;

    /**
     * Default Constructor.
     */
    public MediaServiceImpl() {

        mediaMap = new HashMap<>();


        //zum testen
        addBook(
                new Book("Die Nebel von Avalon ","Marion Zimmer Bradley","3596282225")
        );


    }


    @Override
    public MediaServiceResult addBook(Book book) {

        //no ISBN or invalide
        if (book.getIsbn10() == null || book.getIsbn10().isEmpty() || !validISBN(book.getIsbn10())) {
            return MediaServiceResult.ISBN_INVALID;
        }

        //ISBN duplicate
        if (mediaMap.containsKey(book.getIsbn10())) {
            return MediaServiceResult.ISBN_DUPLICATE;
        }


        //no auther or Title
        if (book.getAuthor() == null || book.getAuthor().isEmpty() || book.getTitle() == null || book.getTitle().isEmpty()) {
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        final Medium medium = mediaMap.put(book.getIsbn10(), book);

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        return null;
    }


    @Override
    public Medium getBook(String isbn) {
        if (mediaMap.containsKey(isbn)) {
            return mediaMap.get(isbn);
        }

        return null;
    }

    @Override
    public Medium[] getBooks() {
        final Medium[] media = mediaMap.values().toArray(new Medium[0]);

        return media;
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }


    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {

        //no ISBN or invalid
        if (book.getIsbn10() == null || isbn == null || book.getIsbn10().isEmpty() || !validISBN(book.getIsbn10()) || !validISBN(isbn)) {

            return MediaServiceResult.ISBN_INVALID;
        }


        if (!isbn.equals(book.getIsbn10())) {
            return MediaServiceResult.ISBN_CONFLICT;
        }

        //ISBN
        if (!mediaMap.containsKey(book.getIsbn10())) {
            return MediaServiceResult.ISBN_NOTFOUND;
        }


        //no author or Title
        if (book.getAuthor() == null || book.getTitle() == null || book.getAuthor().isEmpty() || book.getTitle().isEmpty()) {
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        mediaMap.replace(book.getIsbn10(), book);

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode, Disc disc) {
        return null;
    }

    /**
     * Checking the given string for a valid ISB10 number.
     * @param isbn isbn10 string to check.
     * @return true if valid else false.
     */
    private boolean validISBN(String isbn) {

        final int isbnLength = 10;
        final int isbn10Modulo = 11;

        if (isbn.length() != isbnLength) {
            return false;
        }

        int value = 0;

        for (int i = 0; i < isbn.length() - 1; i++) {
            char c = isbn.charAt(i);
            int number = Character.getNumericValue(c);
            int multiplier = i + 1;
            value += multiplier * number;
        }


        int result = value % isbn10Modulo;

        if (result < 0) {
            result = result + isbn10Modulo;
        }


        int check = Character.getNumericValue(isbn.charAt(isbnLength - 1));


        if (check == result) {
            return true;
        }

        return false;


    }


}
