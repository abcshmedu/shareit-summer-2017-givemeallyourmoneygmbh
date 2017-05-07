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
public class Disc extends Medium {

    private String barcode = null;
    private String director = null;
    private int fsk = -1;

    /**
     * Creates a Disc.
     */
    public Disc() {
        super("");
    }

    /** Creates a disc with given parameters.
     * @param barcode barcode.
     * @param director director.
     * @param fsk FSK.
     * @param title title.
     */
    public Disc(String barcode, String director, int fsk, String title) {
        super(title);
        if(barcode != null)
            this.barcode = barcode.trim();


        if(director != null)
            this.director = director.trim();


        this.fsk = fsk;
    }

    /**Returns the barcode.
     * @return
     */
    public String getBarcode() {
        return barcode;
    }

    /** Returns the Director.
     * @return
     */
    public String getDirector() {
        return director;
    }

    /** Returns FSK.
     * @return
     */
    public int getFsk() {
        return fsk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Disc)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }


        Disc disc = (Disc) o;

        if (fsk != disc.fsk) {
            return false;
        }
        if (barcode != null ? !barcode.equals(disc.barcode) : disc.barcode != null) {
            return false;
        }
        return director != null ? director.equals(disc.director) : disc.director == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + fsk;
        return result;
    }

    @Override
    public String toString() {
        return "Disc{"
                + "barcode='" + barcode + '\''
                + ", director='" + director + '\''
                + ", fsk=" + fsk
                + "} " + super.toString();
    }
}
