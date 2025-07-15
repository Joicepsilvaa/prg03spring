/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface GenericIDao<T> {
    T save(T entity);
    T update(T entity);
    void delete(T entity);
    List<T> findAll();
    T findById(Long id);
    List<T> findByNome(String nome);
}
