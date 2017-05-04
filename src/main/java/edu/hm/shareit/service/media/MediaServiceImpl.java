package edu.hm.shareit.service.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;
import edu.hm.shareit.model.media.Medium;

import java.util.HashMap;
import java.util.Map;

/**
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


    public MediaServiceImpl() {

        mediaMap = new HashMap<>();

    }


    /**
     * Hinzufügen eines Buches
     * Derzeit wird nur ein Fehler und nicht alle auftretenden Fehler zurueckgegeben.
     * Moeglicher Fehler: Ungueltige ISBN
     * Moeglicher Fehler: ISBN bereits vorhanden
     * Moeglicher Fehler: Autor oder Titel fehlt
     * @param book Buch welches hinzugefuegt werden soll
     * @return
     */

    @Override
    public MediaServiceResult addBook(Book book) {

        //no ISBN or invalide
        if(book.getIsbn10() == null || book.getIsbn10().isEmpty() || !validISBN(book.getIsbn10())){
            return MediaServiceResult.ISBN_INVALID;
        }

        //ISBN duplicate
        if(mediaMap.containsKey(book.getIsbn10())){
            return MediaServiceResult.ISBN_DUPLICATE;
        }


        //no auther or Title
        if(book.getAuthor().isEmpty() || book.getTitle().isEmpty()){
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        final Medium medium = mediaMap.put(book.getIsbn10(), book);

        return MediaServiceResult.OK;
    }


    @Override
    public MediaServiceResult addDisc(Disc Disc) {
        return null;
    }

    /**
     * Liefert anhand der ISBN ein Buchexemplar zurueck falls vorhanden.
     * @param isbn isbn des Buches
     * @return gibt das Buch zurück falls vorhanden oder null wenn nicht vorhanden
     */
    @Override
    public Medium getBook(String isbn) {
        if(mediaMap.containsKey(isbn))
            return mediaMap.get(isbn);

        return null;
    }


    /**
     * Liste aller eingetragenen Buecher
     * @return ein Medium Array aller eingetragen Buecher
     */
    @Override
    public Medium[] getBooks() {
        final Medium[] media = mediaMap.values().toArray(new Medium[0]);

        return media;
    }

    @Override
    public Medium[] getDiscs() {
        return new Medium[0];
    }


    /**
     * Bearbeiten eines vorhanden Buches
     * Moeglicher Fehler: ISBN nicht gefunden
     * Moeglicher Fehler: Autor und Titel fehlen
     * @param book Buch welches bearbeitet werden soll
     * @return enum MediaServiceResult welches nur einen der oben genannten Fehler zurueckgibt oder
     *         MediaServiceResult.OK wenn das Buch angelegt worden ist.
     */
    @Override
    public MediaServiceResult updateBook(String isbn, Book book) {

        //no ISBN or invalid
        if(book.getIsbn10()==null || book.getIsbn10().isEmpty() || !validISBN(book.getIsbn10())){
            return MediaServiceResult.ISBN_INVALID;
        }


        if(isbn.equals(book.getIsbn10())){
            return MediaServiceResult.ISBN_CONFLICT;
        }

        //ISBN
        if(!mediaMap.containsKey(book.getIsbn10())){
            return MediaServiceResult.ISBN_NOTFOUND;
        }


        //no author or Title
        if(book.getAuthor().isEmpty() || book.getTitle().isEmpty()){
            return MediaServiceResult.DATA_INVALID;
        }


        //change this to Persistence Layer
        mediaMap.replace(book.getIsbn10(),book);

        return MediaServiceResult.OK;
    }

    @Override
    public MediaServiceResult updateDisc(String barcode,Disc disc) {
        return null;
    }

    /**
     * Ueberprueft ob der uebergebene String eine valide ISBN nach ISBN-10 oder ISBN-13 ist
     * @param isbn
     * @return true wenn gueltig, false wenn nicht gueltig
     */
    private boolean validISBN(String isbn) {

        //https://de.wikipedia.org/wiki/Internationale_Standardbuchnummer
        //TODO: implement validate ISBN-10 //  ISBN-13 derzeit nicht supported xD

        return true;
    }



}
