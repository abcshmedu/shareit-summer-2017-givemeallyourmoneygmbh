package edu.hm.shareit.service.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;
import edu.hm.shareit.model.media.Medium;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 4/22/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public interface MediaService {
    /**
     * Add new book.
     *
     * @param book book.
     * @return returns MediaServiceResult.Ok if no error occurred.
     */
    MediaServiceResult addBook(Book book);

    /**
     * Add new Disc.
     *
     * @param disc disc.
     * @return returns MediaServiceResult.Ok if no error occurred.
     */
    MediaServiceResult addDisc(Disc disc);

    /**
     * Returns book with given isbn.
     *
     * @param isbn isbn13 number as String.
     * @return returns the book as medium object.
     */
    Medium getBook(String isbn);

    /**
     * Returns an array of all available books.
     *
     * @return returns the books as an array of medium objects.
     */
    Medium[] getBooks();

    /**
     * Returns all available discs.
     *
     * @return returns the discs as an array of medium objects.
     */
    Medium[] getDiscs();

    /**
     * Returns disc with given barcode or null if not exist.
     *
     * @param barcode barcode.
     * @return returns the disc as medium object.
     */
    Medium getDisc(String barcode);

    /**
     * Update the book with the given isbn.
     *
     * @param isbn isbn10 of the book which has to be updated.
     * @param book book.
     * @return returns MediaServiceResult.Ok if no error occurred.
     */
    MediaServiceResult updateBook(String isbn, Book book);

    /**
     * Update the disc with the given barcode.
     *
     * @param barcode barcode of the disc which has to be updated.
     * @param disc    disc.
     * @return returns MediaServiceResult.Ok if no error occurred.
     */
    MediaServiceResult updateDisc(String barcode, Disc disc);

}
