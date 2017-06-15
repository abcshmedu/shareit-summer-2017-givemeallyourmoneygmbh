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
public class Medium  implements Serializable {
    //private static final long serialVersionUID = 3642600211882545343L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ID", length = 30)
    private Integer id;

    @Column(name = "TITLE", length = 30)
    private String title;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    Medium() { }

    public Medium(String title) {
        this.title = title;
    }
}
