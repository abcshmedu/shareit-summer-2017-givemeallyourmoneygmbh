package edu.hm.shareit.service.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Disc;
import edu.hm.shareit.model.media.Medium;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 5/4/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

@RunWith(JUnitParamsRunner.class)
public class MediaServiceImplTest {

    private MediaServiceImpl service;

    private final List<Book> books = new ArrayList<>();
    Disc disc = new Disc("B01M72AYG3", " Scott Derrickson", 12, "Doctor Strange 2");

    //public MediaServiceResult mediaServiceResult;

    /**
     * test data for adding books.
     * @return collection of add book data.
     */
    public static Collection<Object[]> addBookData() {

        return Arrays.asList(new Object[][]{

                //ISBN Invalid
                {new Book("Die Biene Maja und Ihre Abenteuer (German Edition)", "Waldemar Bonsels", "1453826048"), MediaServiceResult.ISBN_INVALID},
                {new Book("Die Biene Maja und Ihre Abenteuer (German Edition)", "Waldemar Bonsels", "145282604"), MediaServiceResult.ISBN_INVALID},
                {new Book("Star Wars: The Old Republic - Revan (Star Wars: The Old Republic - Legends)   ", "Drew Karpyshyn  ", "9870345511355   "), MediaServiceResult.ISBN_INVALID},
                {new Book("", "G. M. Berrow", "0316277193"), MediaServiceResult.ISBN_INVALID}, //ISBN-13 = 978-0316277198
                {new Book("Star Wars Rebel Rising", " Beth Revis", "1484780833"), MediaServiceResult.ISBN_INVALID},

                //ISBN Invalid
                {new Book(), MediaServiceResult.ISBN_INVALID},
                {new Book(), MediaServiceResult.ISBN_INVALID},
                {new Book(), MediaServiceResult.ISBN_INVALID},
                {new Book(), MediaServiceResult.ISBN_INVALID},

                //isbn duplicate
                {new Book("Star Wars Rebel Rising", " Beth Revis", "9781484780831"), MediaServiceResult.ISBN_DUPLICATE},


                //Data invalid
                {new Book("", "", "9780316277198"), MediaServiceResult.DATA_INVALID},
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", "", "9780316277198"), MediaServiceResult.DATA_INVALID}, //ISBN-10 = 0316277193
                {new Book("", "G. M. Berrow", "9780316277198"), MediaServiceResult.DATA_INVALID}, //ISBN-13 = 978-0316277198
                {new Book(null, "G. M. Berrow", "9780316277198"), MediaServiceResult.DATA_INVALID}, //ISBN-13 = 978-0316277198
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", null, "9780316277198"), MediaServiceResult.DATA_INVALID}, //ISBN-13 = 978-0316277198
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", "G. M. Berrow", null), MediaServiceResult.ISBN_INVALID}, //ISBN-10 = 0316277193

                //OK
                {new Book("Star Wars: The Old Republic - Revan (Star Wars: The Old Republic - Legends)   ", "Drew Karpyshyn  ", "9780345511355   "), MediaServiceResult.OK},
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", "G. M. Berrow", "9780316277198"), MediaServiceResult.OK}, //ISBN-10 = 0316277193
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", "G. M. Berrow", "978-0316277198"), MediaServiceResult.ISBN_DUPLICATE}, //ISBN-10 = 0316277193

        });

    }



    /**
     * test data for update book.
     * @return collection of update book data.
     */
    public static Collection<Object[]> updateBookData() {

        return Arrays.asList(new Object[][]{


                {"9780201633610", new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", " 9780201633610"), MediaServiceResult.OK},
                {"9780345511355", new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", "9780201633610"), MediaServiceResult.ISBN_CONFLICT},

                {"9780201633610", new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", ""), MediaServiceResult.ISBN_INVALID},
                {"9780201633610", new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", " 980201633610"), MediaServiceResult.ISBN_INVALID},
                {"9780201633610", new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", " 9870201633610"), MediaServiceResult.ISBN_INVALID},

                {null, new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", " 9780201633612"), MediaServiceResult.ISBN_INVALID},
                {"0201633612", new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", null), MediaServiceResult.ISBN_INVALID},

                {"0810983915", new Book(null, null, null), MediaServiceResult.ISBN_INVALID},


                {"9780399553547", new Book("Grumpy Cat", "Golden Books", "9780399553547"), MediaServiceResult.ISBN_NOTFOUND},


                {"9780810983915", new Book("", "", "9780810983915"), MediaServiceResult.DATA_INVALID},
                {"9780810983915", new Book("", "Jeff Kinney", "9780810983915"), MediaServiceResult.DATA_INVALID},
                {"9780810983915", new Book("Dog Days Diary of a Wimpy Kid", "", "9780810983915"), MediaServiceResult.DATA_INVALID},
                {"9780810983915", new Book(null, "", "9780810983915"), MediaServiceResult.DATA_INVALID},
                {"9780810983915", new Book(null, null, "9780810983915"), MediaServiceResult.DATA_INVALID},


                {"9780810983915", new Book("Dog Days Diary of a Wimpy Kid", "Jeff Kinney", "9780810983915"), MediaServiceResult.OK},


        });

    }


    /** Set up for tests.
     */
    @Before
    public void setUp() {
        service = new MediaServiceImpl();
        //Add some Books

        Book book = new Book("Star Wars Rebel Rising", " Beth Revis", "9781484780831");
        books.add(book);

        MediaServiceResult result = service.addBook(book);



        book = new Book("Design Patterns: Elements of Reusable Object-Oriented Software", "Gamma, Erich", "9780201633610");
        books.add(book);
        //result = service.addBook(new Book("Design Patterns: Elements of Reusable Object-Oriented Software","Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John","0201633612"));
        result = service.addBook(book);


        book = new Book("Harry Potter and the Cursed Child", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", "9783551559005");
        books.add(book);
        result = service.addBook(book);



        book = new Book("The Book with No Pictures", "Gamma, Erich; Helm, Richard; Johnson, Ralph E.; Vlissides, John", "9780810983915");
        books.add(book);
        result = service.addBook(book); //B.-J.-Novak, 0803741715

        final MediaServiceResult discResult = service.addDisc(disc);



    }



    /**
     * Test: add books.
     * @param book book
     * @param expected expected result.
     */
    @Test
    @Parameters(method = "addBookData")
    public void testAddBook(final Book book, MediaServiceResult expected) {

        final MediaServiceResult result = service.addBook(book);
        //if(result!= null)
        //    books.add(book);
        assertEquals(expected, result);

    }

    /**
     * Test update books.
     * @param isbn isbn13
     * @param book book
     * @param expected expected result.
     */
    @Test
    @Parameters(method = "updateBookData")
    public void testUpdateBook(final String isbn, final Book book, MediaServiceResult expected) {

        final MediaServiceResult result = service.updateBook(isbn, book);
        assertEquals(expected, result);

    }


    /**
     *
     */
    @Test
    public void testGetBook() {
        final MediaServiceResult result = service.addBook(
                new Book("Die Nebel von Avalon ", "Marion Zimmer Bradley", "9783596282227")
        );

        assertEquals(MediaServiceResult.OK, result);
        final Book book = (Book) service.getBook("9783596282227");
        assertEquals("9783596282227", book.getIsbn());


        assertNull(service.getBook("9783741600500"));

    }


    @Test
    public void testIsbn13() {
        assertEquals(true, service.validISBN13("9781402894626"));
        assertEquals(false, service.validISBN13("9871402894626"));

    }



    @Test
    public void testIsbn10() {
        assertEquals(true, service.validISBN10("0399553541"));
        assertEquals(false, service.validISBN10("0939553541"));

    }


    @Test
    public void testGetBooks() {


        final Medium[] mediums = service.getBooks();
        List<Medium> list = Arrays.asList(mediums);
        assertEquals(books.size(), mediums.length);

        for (Book b: books ) {

            boolean exist = false;
            for (Medium result: list) {
                Book bookResult = (Book) result;
                if(bookResult.getIsbn().equals(b.getIsbn())) {
                    exist = true;
                    break;
                }


            }
            assertTrue(exist);
        }

    }


    /**
     * test data for adding discs.
     * @return collection of add disc data.
     */
    public static Collection<Object[]> addDiscData() {

        return Arrays.asList(new Object[][]{

                //ISBN Invalid

                {new Disc("", " Gareth Edwards", 12, "Rogue One - A Star Wars Story"), MediaServiceResult.BARCODE_INVALID},
                {new Disc("B01N4ECRNP", " ", 12, "Rogue One - A Star Wars Story"), MediaServiceResult.DATA_INVALID},
                {new Disc("B01N4ECRNP", null, 12, "Rogue One - A Star Wars Story"), MediaServiceResult.DATA_INVALID},

                {new Disc("B01N4ECRNP",  " Gareth Edwards", -1, "Rogue One - A Star Wars Story"), MediaServiceResult.DATA_INVALID},

                {new Disc("B01N4ECRNP", " Gareth Edwards", 12, "Rogue One - A Star Wars Story"), MediaServiceResult.OK},
                {new Disc("B01M72AYG3", " Scott Derrickson", 12, "Doctor Strange "), MediaServiceResult.BARCODE_DUPLICATE},
                {new Disc("B01M72AYG3", " Scott Derrickson", 12, "ksfdjsdhf"), MediaServiceResult.BARCODE_DUPLICATE},



        });

    }



    /**
     * Test: add discs.
     * @param disc disc
     * @param expected expected result.
     */
    @Test
    @Parameters(method = "addDiscData")
    public void testAddDisc(final Disc disc, MediaServiceResult expected) {

        final MediaServiceResult result = service.addDisc(disc);
        assertEquals(expected, result);

    }


    @Test
    public void testGetDiscs() {


        final Medium[] mediums = service.getDiscs();
        assertEquals(1, mediums.length);

        final Disc disc = (Disc) mediums[0];
        assertEquals(disc.getBarcode(), disc.getBarcode());



    }

    /**
     * test data for updateing discs.
     * @return collection of update disc data.
     */
    public static Collection<Object[]> updateDiscData() {

        return Arrays.asList(new Object[][]{

                //ISBN Invalid

                {"B01FEK81QA",new Disc("B01M72AYG3", " Gareth Edwards", 12, "Rogue One - A Star Wars Story"), MediaServiceResult.BARCODE_CONFLICT},
                {"B01FEK81QA",new Disc("", " Gareth Edwards", 12, "Rogue One - A Star Wars Story"), MediaServiceResult.BARCODE_INVALID},
                {"B01M72AYG3",new Disc("B01M72AYG3", " Scott Derrickson", 12, "Doctor Strange" ), MediaServiceResult.OK},
                {"B01M72AYG3",new Disc("B01M72AYG3", " ", 12, "Doctor Strange" ), MediaServiceResult.DATA_INVALID},
                {"B01M72AYG3",new Disc("B01M72AYG3", "Scott Derrickson ", -1 , "Doctor Strange" ), MediaServiceResult.DATA_INVALID},
                {"B01M72AYG3",new Disc("B01M72AYG3", "Scott Derrickson ", 12 , "" ), MediaServiceResult.DATA_INVALID},
                {"B01M72AYG3",new Disc("B01M72AYG3", "Scott Derrickson ", 12 , null ), MediaServiceResult.DATA_INVALID},
                {"B01M72AYG3",new Disc(null, "Scott Derrickson ", 12 , "Doctor Strange" ), MediaServiceResult.BARCODE_INVALID},

                {"B01M72AYG3",new Disc("B01M72AYG3", null, 12 , "Doctor Strange" ), MediaServiceResult.DATA_INVALID},
                {"B01FEK81QA",new Disc("B01FEK81QA", null, 12 , "Doctor Strange" ), MediaServiceResult.BARCODE_NOTFOUND},


        });

    }

    @Test
    @Parameters(method = "updateDiscData")
    public void testUpdateDisc(final String barcode, final Disc disc, MediaServiceResult expected) {

        final MediaServiceResult result = service.updateDisc(barcode, disc);
        assertEquals(expected, result);

    }



    @Test
    public void testGetDisc(){


        Disc expected = (Disc) disc;
        Disc result = (Disc)  service.getDisc("B01M72AYG3");;
        assertNotNull(result);
        assertEquals(disc.getBarcode(),result.getBarcode());

        result = (Disc) service.getDisc("B01F4ECRNP");
        assertNull(result);
    }


    @Test
    public void testGetBookByISBN(){
        final Medium result = service.getBook(books.get(0).getIsbn());
        assertNotNull(result);
        assertEquals(books.get(0).getTitle(),result.getTitle());
    }

}