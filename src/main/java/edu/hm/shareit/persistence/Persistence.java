package edu.hm.shareit.persistence;

import java.io.Serializable;
import java.util.List;

/**
 * Organization Hochschule Muenchen FK07
 * Project Software-Architektur, Prof. Dr.-Ing. Axel Bottcher, Praktikum, ShareIt
 * Author I. Colic
 * Date 6/14/2017,
 * Operating System Windows 8.1 Enterprise (Build 9600),
 * Version Java 1.8.0_40
 * System Properties Intel(R) Xeon(R) CPU E5-2660 0 @2.20GHz 2.19 GHz,4 Cores 14.0 GB RAM
 */

public interface Persistence {

    <T, K  extends Serializable> boolean exist(Class<T> tClass, K key);

    <T, K  extends Serializable> T get(Class<T> tClass, K key);

    <T> void add(T element);

    <T> List<T> getAll(Class<T> tClass);

    <T> void update(T element);
}
