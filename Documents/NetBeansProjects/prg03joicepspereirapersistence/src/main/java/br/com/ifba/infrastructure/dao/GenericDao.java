/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.util.JPAUtil;
import br.com.ifba.infrastructure.entity.PersistenceEntity;
import static br.com.ifba.infrastructure.util.JPAUtil.getEntityManager;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @author Joice
 */
public abstract class GenericDao<T extends PersistenceEntity> implements GenericIDao<T> {
    
    protected Class<T> entityClass;
    protected EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
    public GenericDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
        this.entityManager = (EntityManager) JPAUtil.getEntityManager();
    }
    
    public T save(T entity) {
        EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        return entity;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException("Erro ao salvar: " + e.getMessage(), e);
    } finally {
        em.close();
    }
}

    public T update(T entity) {
        EntityManager em = getEntityManager();
    try {
        em.getTransaction().begin();
        entity = em.merge(entity);
        em.getTransaction().commit();
        return entity;
    } catch (Exception e) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
        throw new RuntimeException("Erro ao atualizar: " + e.getMessage(), e);
    } finally {
        em.close();
    }
}
    
    @Override
    public void delete(T entity) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Verifica se a entidade ta gerenciada, se não, faz o merge
            if (!em.contains(entity)) {
                entity = em.merge(entity);
            }

            em.remove(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Erro ao remover entidade: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
    
    @Override
    public List<T> findAll() {
        try {
            return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
        } finally {
            entityManager.close();
        }
    }
    
    @Override
    public T findById(Long id) {
        try {
            return entityManager.find(entityClass, id);
        } finally {
            entityManager.close();
        }
    }
    
    public List<T> findByNome(String nome) {
        EntityManager em = (EntityManager) JPAUtil.getEntityManager();
        try {
            // Obtém o nome da classe da entidade
            String className = entityClass.getSimpleName();
            
            // Cria a consulta JPQL dinâmica
            String jpql = "SELECT e FROM " + className + " e WHERE lower(e.nome) LIKE lower(:nome)";
            
            TypedQuery<T> query = em.createQuery(jpql, entityClass);
            query.setParameter("nome", "%" + nome + "%");
            
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao buscar por nome: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
