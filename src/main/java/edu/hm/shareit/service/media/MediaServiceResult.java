package edu.hm.shareit.service.media;
import javax.ws.rs.core.Response.Status;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic, M. Huebner
 * Date 4/22/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.20GHz,4 Cores 14.0 GB RAM
 */

public enum MediaServiceResult {

    OK (200,Status.OK,""),
    ISBN_INVALID (400,Status.BAD_REQUEST,"ISBN nicht angegeben oder ungueltig"),
    ISBN_DUPLICATE (400,Status.BAD_REQUEST,"ISBN bereits vorhanden"),
    DATA_INVALIDE (400,Status.BAD_REQUEST,"Autor oder Title ungueltig"),
    ISBN_NOTFOUND (404,Status.BAD_REQUEST,"ISBN nicht gefunden"),
    ISBN_CONFLICT (400,Status.BAD_REQUEST,"ISBN Konflikt")
    ;


    private final String detail;
    private final Status status;
    private final int code;


    MediaServiceResult(int code, Status status, String detail) {
        this.code = code;
        this.status = status;
        this.detail = detail;
    }

    public String getDetail() {
        return detail;
    }

    public Status getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "MediaServiceResult{" +
                "message='" + detail + '\'' +
                ", status=" + status +
                ", code=" + code +
                "} " + super.toString();
    }
}

