package edu.hm.shareit.resources.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Medium;
import edu.hm.shareit.service.media.MediaService;
import edu.hm.shareit.service.media.MediaServiceImpl;
import edu.hm.shareit.service.media.MediaServiceResult;
import javax.ws.rs.core.Response;


import javax.ws.rs.*;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 4/22/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

// The Java class will be hosted at the URI path "/media"
@Path("/media")
public class MediaResource {


    MediaService mediaService = new MediaServiceImpl();


    /**
     * Neues Medium Buch anlegen
     * Moglicher Fehler: Ungultige ISBN
     * Moglicher Fehler: ISBN bereits vorhanden
     * Moglicher Fehler: Autor oder Titel fehlt
     * @param book
     * @return
     */
    @Path("/books")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createBook(Book book){
        MediaServiceResult result =  mediaService.addBook(book);

        //check result and create Response

        return Response.ok(result).build();

    }

    /**
     * Eine JSON-Reprasentation eines gespeicherten Buches liefern, falls vorhanden
     * @param isbn
     * @return
     */
    @Path("/books/{isbn}")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getBook(String isbn){
        Medium result =  mediaService.getBook(isbn);
        return Response.ok("Buch").build();
    }

    /**
     * Alle Bucher auflisten
     * @return
     */
    @Path("/books")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getBooks(){
        Medium[] result =  mediaService.getBooks();
        return  Response.ok(result).build();
    }

    /**
     * Daten zu vorhandenem Buch modizieren (JSONDaten enthalten nur die zu modizierenden Attribute)
     * Moglicher Fehler: ISBN nicht gefunden
     * Moglicher Fehler: ISBN soll modiziert werden (also die JSON-Daten enthalten eine andere ISBN als die URI)
     * Moglicher Fehler: Autor und Titel fehlen
     * @param book
     * @return
     */
    @Path("/books/{isbn}")
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateBook(Book book){
        MediaServiceResult result =  mediaService.updateBook(book);
        return Response.ok(result).build();
    }

    //TODO: discs
}
