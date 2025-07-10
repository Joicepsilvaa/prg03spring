/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.entity;

import br.com.ifba.infrastructure.entity.PersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 *
 * @author Joice
 */
@Entity
@Table(name = "cursos")
public class Curso extends PersistenceEntity{
    
    // coluna nome na tabela não pode ser nula
    @Column (name = "nome", nullable = false)
    private String nome;
    
    // coluna codigo_curso na tabela não pode ser nula e deve ser única
    @Column (name = "codigo_curso", nullable = false, unique = true)
    private String codigoCurso;
    
    @Column (name = "coordenador", nullable = false)
    private String coordenador;
    
    // coluna ativo na tabela indica se o curso esta ativo ou não
    @Column (name = "ativo")
    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoCurso() {
        return codigoCurso;
    }

    public void setCodigoCurso(String codigoCurso) {
        this.codigoCurso = codigoCurso;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getCoordenador() {
        return coordenador;
    }

    public void setCoordenador(String coordenador) {
        this.coordenador = coordenador;
    }


}