package edu.hm.shareit.resources.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Medium;
import edu.hm.shareit.service.media.MediaService;
import edu.hm.shareit.service.media.MediaServiceImpl;
import edu.hm.shareit.service.media.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
     * Moeglicher Fehler: Ungueltige ISBN
     * Moeglicher Fehler: ISBN bereits vorhanden
     * Moeglicher Fehler: Autor oder Titel fehlt
     * @param book Buch welches angelegt werden soll
     * @return
     */
    @Path("/books")
    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response createBook(Book book){

        Response response;



        try{
            MediaServiceResult result =  mediaService.addBook(book);

            response = getMediaResponse(result);

        }
        catch(Exception exception){
            response = Response.serverError().build();
        }

        return response;

    }

    /**
     * Eine JSON-Reprasentation eines gespeicherten Buches liefern, falls vorhanden
     * @param isbn isbn des Buches
     * @return
     */
    @Path("/books/{isbn}")
    @GET
    @Produces("application/json")
    @Consumes("application/json")
    public Response getBook(@PathParam("isbn") String isbn){
        Response response;

        try{
            Medium result =  mediaService.getBook(isbn);

            response =  Response.ok(result).build();

        }
        catch(Exception exception){
            response = Response.serverError().build();
        }

        return response;

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


        Response response;


        try{
            Medium[] result =  mediaService.getBooks();
            response =  Response.ok(result).build();

        }
        catch(Exception exception){
            response = Response.serverError().build();
        }

        return response;

    }

    /**
     * Daten zu vorhandenem Buch modizieren (JSON Daten enthalten nur die zu modizierenden Attribute)
     * Moeglicher Fehler: ISBN nicht gefunden
     * Moeglicher Fehler: ISBN soll modiziert werden (also die JSON-Daten enthalten eine andere ISBN als die URI)
     * Moeglicher Fehler: Autor und Titel fehlen
     * @param book Buch welches bearbeitet werden soll
     * @return
     */
    @Path("/books/{isbn}")
    @PUT
    @Produces("application/json")
    @Consumes("application/json")
    //public Response updateBook(Book book){
    public Response updateBook(@PathParam("isbn") String isbn, Book book){

        Response response;

        try{

            MediaServiceResult result =  mediaService.updateBook(isbn,book);
            response = getMediaResponse(result);

            /*Hier oder zum service auslagern?
            //fals aulagern muss das Interface MediaService + MediaServiceImpl angepasst werden
            if(isbn.equals(book.getIsbn())){

                MediaServiceResult result =  mediaService.updateBook(book);
                response = getMediaResponse(result);
            }
            else{
                org.json.JSONObject jsonObject = new org.json.JSONObject();
                jsonObject.put("Code", MediaServiceResult.ISBN_CONFLICT);
                jsonObject.put("detail", MediaServiceResult.ISBN_CONFLICT.getDetail());
                jsonObject.toString();

                response = Response
                        .status(MediaServiceResult.ISBN_CONFLICT.getStatus())
                        .entity(jsonObject)
                        .build();
            }
            */

        }
        catch(Exception exception){
            response = Response.serverError().build();
        }

        return response;


    }

    /**
     * Erzeugt ein Respond Objekt aufgrund des MediaServiceResults
     * @param value MediaServiceResults
     * @return
     */
    private Response getMediaResponse(MediaServiceResult value) {
        Response response;
        switch(value)
        {
            case OK:

                response =  Response.ok(value.getCode()).build();
                break;
            default:
                org.json.JSONObject jsonObject = new org.json.JSONObject();
                jsonObject.put("Code", value.getCode());
                jsonObject.put("detail", value.getDetail());
                jsonObject.toString();

                response = Response
                        .status(value.getStatus())
                        .entity(jsonObject)
                        .build();
                break;
        }
        return response;
    }

    //TODO: discs
}
