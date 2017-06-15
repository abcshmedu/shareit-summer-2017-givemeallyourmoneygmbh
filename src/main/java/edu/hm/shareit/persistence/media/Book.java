package edu.hm.shareit.persistence.media;

import javax.persistence.*;
import java.io.Serializable;


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
@Table(name="TBook")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Book extends Medium implements Serializable {
    //private static final long serialVersionUID = -10359522397308459L;



    @Id
    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AUTHOR")
    private String author;


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
}
