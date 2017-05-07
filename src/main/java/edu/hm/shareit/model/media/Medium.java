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
public class Medium {
    private String title;


    /** Creates a medium with a given title.
     * @param title title.
     */
    public Medium(String title) {
        if (title != null) {
            this.title = title.trim();
        }
    }

    /** Returns the Title of the medium.
     * @return Title as String.
     */
    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (!(o instanceof Medium)) {
            return false;
        }

        Medium medium = (Medium) o;

        return title != null ? title.equals(medium.title) : medium.title == null;

    }

    @Override
    public int hashCode() {
        return title != null ? title.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Medium{"
                + "title='" + title + '\''
                + '}';
    }
}
