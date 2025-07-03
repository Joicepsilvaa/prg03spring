/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.curso.infrastructure.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Joice
 */
public class CursoList {
    
    // Busca todos os cursos cadastrados no banco
    public List<Curso> findAll() {
        EntityManager em = JPAUtil.getEntityManager(); // Abre conexão com o banco
        
        try {
            // Cria a query em JPQL para selecionar todos os cursos
            String jpql = "SELECT c FROM Curso c";
            
            // Prepara a query para executar
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
            
            // Retorna a lista de cursos encontrados
            return query.getResultList();
        } catch (Exception e) {
            // Mostra mensagem em casos de erro
            throw new RuntimeException("Erro ao buscar cursos: " + e.getMessage(), e);
        } finally {
            em.close(); // Fecha a conexão com o banco
        }
    }
    
    // Busca cursos filtrando pelo nome/termo
    public List<Curso> findByNome(String nome) {
        
        // Pega a conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            // query que busca cursos onde tem o texto informado no nome
            String jpql = "SELECT c FROM Curso c WHERE c.nome LIKE :nome";
            
            // Cria e configura a query
            TypedQuery<Curso> query = em.createQuery(jpql, Curso.class);
            
            // Substitui o parâmetro :nome pelo valor recebido (com % para busca parcial)
            query.setParameter("nome", "%" + nome + "%");
            return query.getResultList(); // Retorna os cursos encontrados
            
        } catch (Exception e) {
            //Mostra mensagem de erro em caso de falha
            throw new RuntimeException("Erro ao buscar cursos por nome: " + e.getMessage(), e);
        } finally {
            em.close(); //fecha a conexão 
        }
    }
    
    // Método de pesquisa por ID que auxilia a edição
    public Curso findById(long id) {
        // Abre conexão com o banco
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            // Usa o método pronto do JPA pra buscar pelo ID
            return em.find(Curso.class, id);
            
        } catch (Exception e) {
            // Trata possíveis erros na busca
            throw new RuntimeException("Erro ao buscar curso por ID: " + e.getMessage(), e);
        } finally {
            // Garante que a conexão será fechada
            em.close();
        }
    }
}
