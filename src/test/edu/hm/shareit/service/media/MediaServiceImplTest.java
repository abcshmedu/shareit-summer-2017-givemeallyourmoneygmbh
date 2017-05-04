package edu.hm.shareit.service.media;

import edu.hm.shareit.model.media.Book;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 5/4/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

@RunWith(Parameterized.class)
public class MediaServiceImplTest {

    private MediaServiceImpl service;

    @Parameterized.Parameter(0)
    public Book book;

    /*@Parameterized.Parameter(1)
    public String isbn;
    */

    @Parameterized.Parameter(1)
    public MediaServiceResult mediaServiceResult;


    @Parameterized.Parameters
    public static Collection<Object[]> addBookData(){

        //TODO: isbn-10 verwenden da keine lust -> nur auf isbn-10


        //int code, Response.Status status, String detail
        return Arrays.asList(new Object[][]{

                //ISBN Invalid

                //fuer diesen test muss die validISBN implementiert werden
                {new Book("Die Biene Maja und Ihre Abenteuer (German Edition)","Waldemar Bonsels","1452826048"), MediaServiceResult.ISBN_INVALID},

                //ISBN Invalid
                {new Book(), MediaServiceResult.ISBN_INVALID},
                {new Book(), MediaServiceResult.ISBN_INVALID},
                {new Book(),  MediaServiceResult.ISBN_INVALID},
                {new Book(), MediaServiceResult.ISBN_INVALID},

                //isbn duplicate
                {new Book("Star Wars Rebel Rising"," Beth Revis","1484780833"), MediaServiceResult.ISBN_DUPLICATE},

                //Data invalid
                {new Book("","","0316277193"), MediaServiceResult.DATA_INVALID},
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", "","0316277193"),  MediaServiceResult.DATA_INVALID}, //ISBN-10 = 0316277193
                {new Book("", "G. M. Berrow","978-0316277198"),  MediaServiceResult.DATA_INVALID}, //ISBN-13 = 978-0316277198

                //OK
                {new Book("Star Wars: The Old Republic - Revan (Star Wars: The Old Republic - Legends)   ","Drew Karpyshyn  ", "9780345511355   "),  MediaServiceResult.OK},
                {new Book("My Little Pony: Fluttershy and the Fine Furry Friends Fair (My Little Pony Chapter Books)", "G. M. Berrow","0316277193"), MediaServiceResult.OK}, //ISBN-10 = 0316277193

        });

    }


    /**
     *
     * @throws Exception
     */
    @Before
    public void setUp() {
        service = new MediaServiceImpl();
        //Add some Books

        final MediaServiceResult result = service.addBook(new Book("Star Wars Rebel Rising"," Beth Revis","1484780833"));



        assertEquals(MediaServiceResult.OK, result);



    }


    @Test
    @Parameterized.Parameters
    public void testAddBook( ) {

        final MediaServiceResult result = service.addBook(book);
        assertEquals(mediaServiceResult,result);

    }

}