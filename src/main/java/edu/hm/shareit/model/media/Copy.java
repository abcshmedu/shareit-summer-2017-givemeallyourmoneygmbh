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
public class Copy {

    private Medium medium;
    private String owner;

    /** Creates a Copy of an existent medium.
     * @param owner owner of the copy.
     * @param medium medium.
     */
    public Copy(String owner, Medium medium) {
        this.medium = medium;
        this.owner = owner;
    }

    /** Returns the medium.
     * @return
     */
    public Medium getMedium() {
        return medium;
    }

    /** Returns the Owner.
     * @return
     */
    public String getUsername() {
        return owner;
    }
}
