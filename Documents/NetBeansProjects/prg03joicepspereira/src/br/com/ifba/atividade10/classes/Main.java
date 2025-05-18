/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10.classes;

import java.util.Arrays;

/**
 *
 * @author Joice
 */

public class Main {
    public static void main(String[] args) {
        // 1) Criação de perfis de usuário
        PerfilUsuario admin = new PerfilUsuario(
            1L,
            "Administrador",
            Arrays.asList("CRIAR", "EDITAR", "DELETAR")
        );
        PerfilUsuario editor = new PerfilUsuario(
            2L,
            "Editor",
            Arrays.asList("CRIAR", "EDITAR")
        );

        // 2) Criação de usuários com seus perfis
        Usuario joao = new Usuario(1L, admin, "joao", "joao@email.com", "1234");
        Usuario maria = new Usuario(2L, editor, "maria", "maria@email.com", "abcd");

        // 3) Testes de login
        joao.logar("1234");      // login correto
        joao.logar("12345");     // login incorreto (gera log de erro)
        
        // Criando sessões para João
        Sessao s1 = joao.criarSessao();
        Sessao s2 = joao.criarSessao();

        // Login de Maria
        maria.logar("abcd");
        Sessao s3 = maria.criarSessao();

        // 4) Impressão dos usuários
        System.out.println("\n-- USUARIOS --");
        System.out.println(joao);
        System.out.println(maria);

        // Impressão dos logs do João
        System.out.println("\n-- LOGS DE JOAO --");
        System.out.print(joao.logsToString());

        // Impressão das sessões do João
        System.out.println("-- SESSOES DE JOAO --");
        for (Sessao s : joao.getSessoes()) {
            System.out.println(s);
        }
    }
}
