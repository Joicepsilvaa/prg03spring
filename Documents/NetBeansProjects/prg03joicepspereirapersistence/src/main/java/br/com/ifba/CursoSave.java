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
public class CursoSave {
    
    // Método que salva um curso no banco
    public void save(Curso curso) {
        // Pega a conexão com o banco (EntityManager)
        EntityManager em = JPAUtil.getEntityManager();

           try {
               em.getTransaction().begin(); // Começa uma transação
               em.persist(curso); // Manda salvar o curso no banco
               em.getTransaction().commit(); // Confirma tudo se deu certo
               
           } catch (Exception e) {
               // Se deu erro, verifica se a transação tá ativa pra desfazer
               if (em.getTransaction().isActive()) {
                   em.getTransaction().rollback();
               }
               //Exibe uma mensagem de erro
               throw new RuntimeException("Erro ao salvar curso: " + e.getMessage(), e);
           } finally {
               em.close(); // Fecha a conexão com o banco, mesmo se der erro
           }
       }
}
 