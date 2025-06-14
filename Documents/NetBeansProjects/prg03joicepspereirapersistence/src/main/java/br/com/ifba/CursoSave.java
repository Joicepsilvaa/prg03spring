/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Joice
 */
public class CursoSave {
    
    public static void main(String[] args) {
        // Cria o EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("prg03persistenciaPU");
    
        // Cria o EntityManager
        EntityManager em = emf.createEntityManager();
        
        try {
            // Criar um novo curso
            Curso novoCurso = new Curso();
            novoCurso.setNome("POO");
            novoCurso.setCodigoCurso("prg03");
            novoCurso.setAtivo(true);
            
            // Iniciar transação
            em.getTransaction().begin();
            
            // Persistie o curso
            em.persist(novoCurso);
            
            // Commita a transação
            em.getTransaction().commit();
            
            System.out.println("Curso salvo com sucesso! ID: " + novoCurso.getId());
            
        } catch (Exception e) {
            // Em caso de erro, fazer rollback
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.err.println("Erro ao salvar curso:");
            e.printStackTrace();
        } finally {
            //Fecha os recursos
            em.close();
            emf.close();
        }
    }
}
 