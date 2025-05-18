/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10.classes;

import java.time.LocalDateTime;

/**
 *
 * @author Joice
 */

public class LogAuditoria {
    private Long id;
    private Usuario usuario; //referência ao usuário que gerou o log
    private String acao; // descrição da ação realizada (ex: login, falha etc.)
    private LocalDateTime dataHora; //data e hora do evento
    private String ip; // IP de onde veio a ação

    //construtor com todos os campos
    public LogAuditoria(Long id, Usuario usuario, String acao, LocalDateTime dataHora, String ip) {
        this.id = id;
        this.usuario = usuario;
        this.acao = acao;
        this.dataHora = dataHora;
        this.ip = ip;
    }

    // Getters e setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }
    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }

    public String getAcao() { 
        return acao; 
    }
    public void setAcao(String acao) { 
        this.acao = acao; 
    }

    public LocalDateTime getDataHora() { 
        return dataHora; 
    }
    public void setDataHora(LocalDateTime dataHora) { 
        this.dataHora = dataHora; 
    }

    public String getIp() { 
        return ip; 
    }
    public void setIp(String ip) { 
        this.ip = ip; 
    }

    @Override
    public String toString() {
        // Exibe o log mostrando nome do usuário
        return "LogAuditoria{id=" + id +
               ", usuario=" + usuario.getNomeUsuario() +
               ", acao='" + acao + '\'' +
               ", dataHora=" + dataHora +
               ", ip='" + ip + '\'' +
               '}';
    }
}
