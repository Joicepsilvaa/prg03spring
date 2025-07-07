/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import java.util.List;

/**
 *
 * @author Joice
 */
public interface CursoIService {
    
    Curso saveCurso(Curso curso);
    Curso updateCurso(Curso curso);
    void deleteCurso(Curso curso);
    List<Curso> findAllCursos();
    List<Curso> findCursosByNome(String nome);
    Curso findCursoById(Long id);
}
