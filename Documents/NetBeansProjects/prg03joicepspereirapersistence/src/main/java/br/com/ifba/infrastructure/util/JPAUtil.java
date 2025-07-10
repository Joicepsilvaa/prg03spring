/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.infrastructure.util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author Joice
 */
public class JPAUtil {
    // Método que guarda a conexão principal
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("prg03persistenciaPU");
    
    // Método que devolve um EntityManager novo pra cada operação
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    // Método pra fechar a conexão quando não for mais precisar
    public static void close() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
