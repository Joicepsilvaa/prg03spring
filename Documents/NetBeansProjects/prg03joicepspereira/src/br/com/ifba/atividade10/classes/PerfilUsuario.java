/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10.classes;

import java.util.List;

/**
 *
 * @author Joice
 */


public class PerfilUsuario {

    private Long id;
    private String descricao;  // Descrição do perfil (ex: Administrador)
    private List<String> permissoes; // Lista de permissões (ex: CRIAR, EDITAR)

    // Construtor
    public PerfilUsuario(Long id, String descricao, List<String> permissoes) {
        this.id = id;
        this.descricao = descricao;
        this.permissoes = permissoes;
    }

    // Getters e setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public String getDescricao() { 
        return descricao; 
    }
    public void setDescricao(String descricao) { 
        this.descricao = descricao; 
    }

    public List<String> getPermissoes() { 
        return permissoes; 
    }
    public void setPermissoes(List<String> permissoes) { 
        this.permissoes = permissoes; 
    }

    @Override
    public String toString() {
        return "PerfilUsuario{id=" + id + ", descricao='" + descricao + "', permissoes=" + permissoes + '}';
    }
}
