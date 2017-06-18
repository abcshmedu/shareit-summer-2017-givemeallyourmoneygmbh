package edu.hm.shareit.persistence.media;

import javax.persistence.*;


/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/13/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */
@Entity
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Book  {

    private static final long serialVersionUID = -10359522397308459L;
    @Id
    private String isbn;

    private String author;

    @Column(name = "TITLE", length = 30)
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    protected Book(){}

    public Book(String isbn, String author) {
        this.isbn = isbn;
        this.author = author;
    }

    public Book(String title, String isbn, String author) {

        this.isbn = isbn;
        this.author = author;
        this.title = title;
    }
}
