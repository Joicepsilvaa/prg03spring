/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.dao;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Joice
 */
@Repository
public abstract class GenericDao<T extends PersistenceEntity> implements GenericIDao<T> {
    
    protected Class<T> entityClass;
    
    @PersistenceContext
    protected EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
    public GenericDao() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
    }
    
    @Override
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }
    
    @Override
    public T update(T entity) {
        return entityManager.merge(entity);
    }
    
    @Override
    public void delete(T entity) {
        if (!entityManager.contains(entity)) {
            entity = entityManager.merge(entity);
        }
        entityManager.remove(entity);
    }
    
    @Override
    public List<T> findAll() {
        return entityManager.createQuery("FROM " + entityClass.getName(), entityClass).getResultList();
    }
    
    @Override
    public T findById(Long id) {
        return entityManager.find(entityClass, id);
    }
    
    @Override
    public List<T> findByNome(String nome) {
        String className = entityClass.getSimpleName();
        String jpql = "SELECT e FROM " + className + " e WHERE lower(e.nome) LIKE lower(:nome)";
        
        TypedQuery<T> query = entityManager.createQuery(jpql, entityClass);
        query.setParameter("nome", "%" + nome + "%");
        
        return query.getResultList();
    }
}