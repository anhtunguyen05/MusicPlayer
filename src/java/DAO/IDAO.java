/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.util.List;

/**
 *
 * @author nguye
 */
public interface IDAO <T>{
    List<T> get();
    void add(T entity);
    void update(T entity);
    T get(String idd);
    void delete(String idd);
}
