package edu.hm.shareit.model.media;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 4/21/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */
public class Book extends Medium{

    private String auther;
    private String isbn;



    public Book() {
        super("");
    }

    public Book(String title, String auther, String isbn) {
        super(title);
        this.auther = auther;
        this.isbn = isbn;
    }

    public String getAuther() {
        return auther;
    }

    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        if (!super.equals(o)) return false;

        Book book = (Book) o;

        if (auther != null ? !auther.equals(book.auther) : book.auther != null) return false;
        return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (auther != null ? auther.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "auther='" + auther + '\'' +
                ", isbn='" + isbn + '\'' +
                "} " + super.toString();
    }
}
