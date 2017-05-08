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
public class Book extends Medium {

    private String author = null;
    private String isbn = null;


    /**
     * Creates a book.
     */
    public Book() {
        super("");
    }

    /** Creates a book with given parameters.
     * @param title Title of the book.
     * @param author author of the book.
     * @param isbn isbn13 of the book.
     */
    public Book(String title, String author, String isbn) {
        super(title);

        if (author != null) {
            this.author = author.trim();
        }

        if (isbn != null) {
            this.isbn = isbn.trim();
            this.isbn = this.isbn.replace("-","");
        }
    }

    /**
     * Returns the author of this book.
     * @return author.
     */
    public String getAuthor() {
        return author;
    }

    /** Rteurns the isbn13 of this book.
     * @return isbn13.
     */
    public String getIsbn() {
        return isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Book)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Book book = (Book) o;

        if (author != null ? !author.equals(book.author) : book.author != null) {
            return false;
        }

        return isbn != null ? isbn.equals(book.isbn) : book.isbn == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{"
                + "author='" + author + '\''
                + ", isbn='" + isbn + '\''
                + "} " + super.toString();
    }
}
