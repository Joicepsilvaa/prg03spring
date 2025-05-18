/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.atividade10.classes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Joice
 */


public class Usuario {
    private Long id;
    private PerfilUsuario perfil; // Perfil de permissões do usuário
    private String nomeUsuario;
    private String email;
    private String senha;
    private LocalDateTime ultimoLogin;
    private boolean ativo;

    // Lista de logs (ações realizadas)
    private final List<LogAuditoria> logs = new ArrayList<>();

    // Lista de sessões iniciadas
    private final List<Sessao> sessoes = new ArrayList<>();

    // Contadores estáticos para gerar IDs únicos de logs e sessões
    private static long logCounter = 0;
    private static long sessionCounter = 0;

    // Construtor completo
    public Usuario(Long id, PerfilUsuario perfil, String nomeUsuario, String email, String senha) {
        this.id = id;
        this.perfil = perfil;
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.ativo = true;
    }

    // Getters e setters
    public Long getId() { 
        return id; 
    }
    public void setId(Long id) { 
        this.id = id; 
    }

    public PerfilUsuario getPerfil() { 
        return perfil; 
    }
    public void setPerfil(PerfilUsuario perfil) { 
        this.perfil = perfil; 
    }

    public static long getLogCounter() { 
        return logCounter; 
    }
    public static void setLogCounter(long logCounter) { 
        Usuario.logCounter = logCounter; 
    }

    public static long getSessionCounter() { 
        return sessionCounter; 
    }
    public static void setSessionCounter(long sessionCounter) { 
        Usuario.sessionCounter = sessionCounter; 
    }

    public String getNomeUsuario() { 
        return nomeUsuario; 
    }
    public void setNomeUsuario(String nomeUsuario) { 
        this.nomeUsuario = nomeUsuario; 
    }

    public String getEmail() { 
        return email; 
    }
    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getSenha() { 
        return senha; 
    }
    public void setSenha(String senha) { 
        this.senha = senha; 
    }

    public LocalDateTime getUltimoLogin() { 
        return ultimoLogin; 
    }
    public void setUltimoLogin(LocalDateTime ultimoLogin) { 
        this.ultimoLogin = ultimoLogin; 
    }

    public boolean isAtivo() { 
        return ativo; 
    }
    public void setAtivo(boolean ativo) { 
        this.ativo = ativo; 
    }

    public List<LogAuditoria> getLogs() { 
        return logs; 
    }
    public List<Sessao> getSessoes() { 
        return sessoes; 
    }

    @Override
    public String toString() {
        return "Usuario{" +
               "id=" + id +
               ", nomeUsuario='" + nomeUsuario + '\'' +
               ", email='" + email + '\'' +
               ", ativo=" + ativo +
               ", ultimoLogin=" + ultimoLogin +
               ", #logs=" + logs.size() +
               ", #sessoes=" + sessoes.size() +
               '}';
    }

    // Gera string com todos os logs do usuário
    public String logsToString() {
        StringBuilder sb = new StringBuilder();
        for (LogAuditoria l : logs) {
            sb.append(l).append("\n");
        }
        return sb.toString();
    }

    // Valida login e registra log
    public void logar(String senhaDigitada) {
        if (this.senha.equals(senhaDigitada)) {
            this.ultimoLogin = LocalDateTime.now();
            long newId = ++logCounter;
            LogAuditoria log = new LogAuditoria(newId, this, "Login bem-sucedido", LocalDateTime.now(), "127.0.0.1");
            logs.add(log);
            System.out.println("Log criado: " + log);
        } else {
            long newId = ++logCounter;
            LogAuditoria log = new LogAuditoria(newId, this, "Tentativa de login falhou", LocalDateTime.now(), "127.0.0.1");
            logs.add(log);
            System.out.println("Senha incorreta. Log criado: " + log);
        }
    }

    // Cria uma nova sessão com token único
    public Sessao criarSessao() {
        long newId = ++sessionCounter;
        String token = "token_" + this.nomeUsuario + "_" + System.currentTimeMillis();
        Sessao sessao = new Sessao(newId, this, token);
        sessoes.add(sessao);
        System.out.println("Sessao criada: " + sessao);
        return sessao;
    }
}