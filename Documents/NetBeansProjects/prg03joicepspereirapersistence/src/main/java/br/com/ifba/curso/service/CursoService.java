/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;

/**
 *
 * @author Joice
 */
public class CursoService {
    
    private final CursoIDao cursoDAO;
    
    public CursoService() {
        this.cursoDAO = new CursoDao();
    }
    
    public Curso saveCurso(Curso curso){
        
        return null;
        
    // restante dos métodos a serem implementados
    }
}
