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
@Table(name="TMedium")
@Inheritance(strategy= InheritanceType.TABLE_PER_CLASS)
public class Disc extends Medium  implements Serializable {
    @Id
    @Column(name = "BARCODE")
    private String barcode;

    @Column(name = "DIRECTOR")
    private String director;

    @Column(name = "FSK")
    private int fsk;

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getFsk() {
        return fsk;
    }

    public void setFsk(int fsk) {
        this.fsk = fsk;
    }
}
