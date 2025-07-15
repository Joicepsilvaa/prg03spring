/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.controller;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.service.CursoIService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
/**
 * Controller para operações com Curso
 * @author Joice
 */
@Controller
public class CursoController implements CursoIController{
    
    @Autowired
    private CursoIService cursoService;
           
    public void save(Curso curso) throws RuntimeException {
        cursoService.save(curso);
    }

    public void update(Curso curso) throws RuntimeException {
        cursoService.update(curso);
    }

    public void delete(Curso curso) throws RuntimeException {
        cursoService.delete(curso);
    }


    public List<Curso> findAll() throws RuntimeException {
        return cursoService.findAll();
    }

    @Override
    public Curso findById(Long id) throws RuntimeException {
       return cursoService.findById(id);
    }

    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {
        return cursoService.findByNome(nome);
    }

}
