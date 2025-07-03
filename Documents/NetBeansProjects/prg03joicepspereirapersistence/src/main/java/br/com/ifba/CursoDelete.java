/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.infrastructure.JPAUtil;
import javax.persistence.EntityManager;

/**
 *
 * @author Joice
 */
public class CursoDelete {
    
    // Método para deletar um curso pelo ID
    public void delete(long id) {
        // Cria uma instancia do EntityManager o banco
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin(); // Inicia uma transação no bd
            Curso curso = em.find(Curso.class, id); // Procura o curso pelo ID
            //Confirma se o curso existe antes de tentar remover
            if (curso != null) {
                em.remove(curso); // Remove o curso do bd
            }
            em.getTransaction().commit(); // Confirma a transação se tudo ocorrer bem
        } catch (Exception e) {
            
            // Se ocorrer algum erro verifica se a transação está ativa e faz rollback
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            // Lança uma exceção
            throw new RuntimeException("Erro ao remover curso: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
