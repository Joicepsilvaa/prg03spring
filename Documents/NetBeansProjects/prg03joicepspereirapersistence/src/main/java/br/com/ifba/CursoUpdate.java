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
public class CursoUpdate {
    
    //Método que atualiza as informações de um curso no banco
    public void update(Curso curso) {
        // Pega a conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            em.getTransaction().begin(); // Inicia a transação
            em.merge(curso); // Atualiza o curso no banco
            em.getTransaction().commit(); // Confirma as alterações se der tudo certo
            
        } catch (Exception e) {
            //verifica se a transação tá aberta pra cancelar em caso de erro
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            //Mostra uma mensagem de erro
            throw new RuntimeException("Erro ao atualizar curso: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
