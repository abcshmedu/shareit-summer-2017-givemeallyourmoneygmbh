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
    MediaServiceResult addBook(Book book);
    MediaServiceResult addDisc(Disc Disc);

    Medium getBook(String isbn);
    Medium[] getBooks();
    Medium[] getDiscs();
    MediaServiceResult updateBook(String isbn, Book book);
    MediaServiceResult updateDisc(String barcode,Disc disc);

}
