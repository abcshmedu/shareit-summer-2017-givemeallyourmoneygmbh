package edu.hm.shareit.service.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;
import edu.hm.shareit.model.media.Medium;
import edu.hm.shareit.persistence.MediaPersistence;

import javax.inject.Inject;
import java.util.List;

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

    @Inject
    private MediaPersistence persistence; //= new MediaPersistenceImpl();
    //in der naechsten Aufgabe auslagern nach Persistence Layer
    //private static Map<String, Medium> bookMap = new HashMap<>();

    //private static Map<String, Medium> discMap =new HashMap<>();

    /**
     * Default Constructor.
     */
    /*@Inject
    public MediaServiceImpl(MediaPersistence persistence) {
        this.persistence = persistence;
        //tokenService = new AuthenticationImpl();
    }*/
    public MediaServiceImpl() {


    }

    @Override
    public MediaServiceResult addBook(Book book) {

        //no ISBN or invalid
        if (book.getIsbn() == null || book.getIsbn().isEmpty() || !validISBN13(book.getIsbn())) {
            return MediaServiceResult.ISBN_INVALID;
        }

        //ISBN duplicate
        //Book oldBook = persistence.getBook(book.getIsbn());

        //if(oldBook != null)
//            return MediaServiceResult.ISBN_DUPLICATE;


        if(persistence.existBook(book.getIsbn()))
            return MediaServiceResult.ISBN_DUPLICATE;

//        if (bookMap.containsKey(book.getIsbn())) {
//            return MediaServiceResult.ISBN_DUPLICATE;
//        }


        //no auther or Title
        if (book.getAuthor() == null || book.getAuthor().isEmpty() || book.getTitle() == null || book.getTitle().isEmpty()) {
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        //final Medium medium = bookMap.put(book.getIsbn(), book);

        persistence.addBook(book);



        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult addDisc(Disc disc) {
        //no Barcode or invalid
        //barcode check?
        if (disc.getBarcode() == null || disc.getBarcode().isEmpty()) {
            return MediaServiceResult.BARCODE_INVALID;
        }

        //Disc oldDisc = persistence.getDisc(disc.getBarcode());

        if(persistence.existDisc(disc.getBarcode()))
            return MediaServiceResult.BARCODE_DUPLICATE;

        //barcode duplicate
        //if (discMap.containsKey(disc.getBarcode())) {
        //    return MediaServiceResult.BARCODE_DUPLICATE;
        //}


        //no director or Title etc
        if (disc.getDirector() == null || disc.getDirector().isEmpty() || disc.getTitle() == null || disc.getTitle().isEmpty() || disc.getFsk() < 0) {
            return MediaServiceResult.DATA_INVALID;
        }

        //change this to Persistence Layer
        //final Medium medium = discMap.put(disc.getBarcode(), disc);
        persistence.addDisc(disc);

        return MediaServiceResult.OK;
    }


    @Override
    public Medium getBook(String isbn) {

        return persistence.getBook(isbn);
        //if (bookMap.containsKey(isbn)) {
        //    return bookMap.get(isbn);
        //}
    }

    @Override
    public Medium[] getBooks() {

        List<Book> list = persistence.getAllBooks();
        return list.toArray(new Book[list.size()]);

        //final Book[] media =  persistence.getAllBooks();
        //final Medium[] media = bookMap.values().toArray(new Medium[0]);


    }

    @Override
    public Medium[] getDiscs() {
        //final Disc[] media =  persistence.getDiscs();
        //final Medium[] media = discMap.values().toArray(new Medium[0]);
        //return media;

        List<Disc> list = persistence.getAllDiscs();
        return list.toArray(new Disc[list.size()]);


    }

    @Override
    public Medium getDisc(String barcode) {
        /*
        if (discMap.containsKey(barcode)) {
            return discMap.get(barcode);
        }
        return null;
        */

        return persistence.getDisc(barcode);
    }


    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {

        //no ISBN or invalid
        if (book.getIsbn() == null || isbn == null || book.getIsbn().isEmpty() || !validISBN13(book.getIsbn()) || !validISBN13(isbn)) {

            return MediaServiceResult.ISBN_INVALID;
        }


        if (!isbn.equals(book.getIsbn())) {
            return MediaServiceResult.ISBN_CONFLICT;
        }

        //ISBN
        /*
        if (!bookMap.containsKey(book.getIsbn())) {
            return MediaServiceResult.ISBN_NOTFOUND;
        }
        */

        //ISBN
        /*
        Book oldBook = persistence.getBook(book.getIsbn());
        if(oldBook == null)
            return MediaServiceResult.ISBN_NOTFOUND;
        */

        if(!persistence.existBook(book.getIsbn()))
            return MediaServiceResult.ISBN_NOTFOUND;

        //no author or Title
        if (book.getAuthor() == null || book.getTitle() == null || book.getAuthor().isEmpty() || book.getTitle().isEmpty()) {
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        //bookMap.replace(book.getIsbn(), book);
        persistence.updateBook(book);

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode, Disc disc) {
        //no barcode or invalid
        if (disc.getBarcode() == null || barcode == null || disc.getBarcode().isEmpty()) {

            return MediaServiceResult.BARCODE_INVALID;
        }


        if (!barcode.equals(disc.getBarcode())) {
            return MediaServiceResult.BARCODE_CONFLICT;
        }


        /*
        if (!discMap.containsKey(disc.getBarcode())) {
            return MediaServiceResult.BARCODE_NOTFOUND;
        }
        */

        //BARCODE
        if(!persistence.existDisc(disc.getBarcode()))
            return MediaServiceResult.BARCODE_NOTFOUND;

        /*
        Disc oldDisc = persistence.getDisc(disc.getBarcode());
        if(oldDisc == null)
            return MediaServiceResult.BARCODE_NOTFOUND;
        */

        //no author or Title
        if (disc.getDirector() == null || disc.getTitle() == null || disc.getTitle().isEmpty() || disc.getDirector().isEmpty() || disc.getFsk() <0 ) {
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        //discMap.replace(disc.getBarcode(), disc);
        persistence.updateDisc(disc);

        return MediaServiceResult.OK;
    }


    /**
     * Checking the given string for a valid ISB10 number.
     *
     * @param isbn isbn10 string to check.
     * @return true if valid else false.
     */
    boolean validISBN10(String isbn) {

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


    /**
     * Checking the given string for a valid ISB13 number.
     *
     * @param isbn isbn13 string to check.
     * @return true if valid else false.
     */
    boolean validISBN13(String isbn) {

        final int isbnLength = 13;
        final int modulo = 10;
        final int base = 3;


        if (isbn.length() != isbnLength) {
            return false;
        }

        int value = 0;

        //sum
        for (int i = 0; i < isbn.length() - 1; i++) {
            char c = isbn.charAt(i);
            int number = Character.getNumericValue(c);

            int exponent = (i + 2) % 2;
            value += number * (Math.pow(base, exponent));

        }


        int result = (modulo - value % modulo) % modulo;

        if (result < 0) {
            result = result + modulo;
        }


        int check = Character.getNumericValue(isbn.charAt(isbnLength - 1));


        if (check == result) {
            return true;
        }

        return false;


    }

}
