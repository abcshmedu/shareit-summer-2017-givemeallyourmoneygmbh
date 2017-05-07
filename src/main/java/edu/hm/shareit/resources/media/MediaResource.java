package edu.hm.shareit.resources.media;

import edu.hm.shareit.model.media.Book;
import edu.hm.shareit.model.media.Medium;
import edu.hm.shareit.service.media.MediaService;
import edu.hm.shareit.service.media.MediaServiceImpl;
import edu.hm.shareit.service.media.MediaServiceResult;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
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


    private MediaService mediaService = new MediaServiceImpl();



    /**
     * Creates a new Medium.
     * possible Errors:
     * ISBN_INVALID - isbn is not a valide isbn13 string.
     * ISBN_DUPLICATE - a book with the same isbn already exist
     * DATA_INVALID - missing author and title
     * @param book new book.
     * @return Returns ok if no error occurred or the errorcode and details.
     */
    @Path("/books")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createBook(Book book) {

        Response response;


        try {
            MediaServiceResult result = mediaService.addBook(book);

            response = getMediaResponse(result);

        } catch (Exception exception) {
            response = Response.serverError().build();
        }

        return response;

    }

    /**
     * Returns a JSON representation of the book with the given isbn or null if the book doesn't exist.
     * @param isbn isbn13.
     * @return Returns ok if no error occurred or the errorcode and details.
     */
    @Path("/books/{isbn}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("isbn") String isbn) {
        Response response;

        try {
            Medium result = mediaService.getBook(isbn);
            response = Response.ok(result).build();

        } catch (Exception exception) {
            response = Response.serverError().build();
        }

        return response;

    }


    /**
     * Returns all available books.
     * @return Json representation of an array of books.
     */

    @Path("/books")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getBooks() {


        Response response;


        try {
            Medium[] result = mediaService.getBooks();
            response = Response.ok(result).build();

        } catch (Exception exception) {
            response = Response.serverError().build();
        }

        return response;

    }


    /**
     * Update an existing  book.
     * possible Errors:
     * ISBN_NOTFOUND - a book with the given isbn doesn't exist.
     * ISBN_INVALID - isbn is not a valide isbn13 string.
     * ISBN_CONFLICT - not matching isbn strings between the given book and the new data.
     * DATA_INVALID - missing author and title
     *
     * @param book book.
     * @param isbn isbn13.
     * @return Returns ok if no error occurred or the errorcode and details.
     */
    @Path("/books/{isbn}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(@PathParam("isbn") String isbn, Book book) {

        Response response;

        try {

            MediaServiceResult result = mediaService.updateBook(isbn, book);
            response = getMediaResponse(result);

        } catch (Exception exception) {
            response = Response.serverError().build();
        }

        return response;


    }

    /**
     * Creates a Respond Object on the basis of MediaServiceResult.
     * @param value MediaServiceResults
     * @return Respond Object.
     */
    private Response getMediaResponse(MediaServiceResult value) {
        Response response;
        switch (value) {
            case OK:

                response = Response.ok(value.getCode()).build();
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
