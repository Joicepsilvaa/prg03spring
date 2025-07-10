/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoDao;
import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;

/**
 *
 * @author Joice
 */
public class CursoService implements CursoIService {
    
    // Cria uma instância do DAO para acessar o banco de dados
    private final CursoIDao cursoDao = new CursoDao();
    
    // Metodo pra salvar um novo curso no sistema
    @Override
    public void save(Curso curso) throws RuntimeException {
        // Validação básica
        if (curso == null) {
            throw new RuntimeException("Dados do Curso não preenchidos!");
        }
        
        // Valida campos obrigatórios
        if (StringUtil.isNullOrEmpty(curso.getNome()) ||
            StringUtil.isNullOrEmpty(curso.getCodigoCurso())) {
            throw new IllegalArgumentException("Nome e código do curso são obrigatórios!");
        }
        
        // Valida se nome contém apenas letras, números e espaços
        if (!StringUtil.isAlphanumericWithSpaces(curso.getNome())) {
            throw new IllegalArgumentException("Nome do curso deve conter apenas letras, números e espaços!");
        }
        
        // Valida se código contém apenas letras e números (sem espaços)
        if (!StringUtil.isAlphanumeric(curso.getCodigoCurso())) {
            throw new IllegalArgumentException("Código do curso deve conter apenas letras e números!");
        }
        
        // Valida se coordenador não contém caracteres especiais
        if (curso.getCoordenador() != null && 
            StringUtil.containsSpecialCharacters(curso.getCoordenador())) {
            throw new IllegalArgumentException("Nome do coordenador não pode conter caracteres especiais!");
        }
        
        // Remove espaços extras
        curso.setNome(StringUtil.trimExtraSpaces(curso.getNome()));
        curso.setCodigoCurso(StringUtil.trimExtraSpaces(curso.getCodigoCurso()));
        if (curso.getCoordenador() != null) {
            curso.setCoordenador(StringUtil.trimExtraSpaces(curso.getCoordenador()));
        }
        
        cursoDao.save(curso);
    }

    @Override
    public void update(Curso curso) throws RuntimeException, EntityNotFoundException {
        // Validação de campos vazio
        if (curso == null) {
            throw new IllegalArgumentException("Dados não preenchidos.");
        }
        
        // Valida campos obrigatórios
        if (StringUtil.isNullOrEmpty(curso.getNome()) || 
            StringUtil.isNullOrEmpty(curso.getCodigoCurso())) {
            throw new IllegalArgumentException("Nome e código do curso são obrigatórios!");
        }
        
        // Valida formato do código
        if (!StringUtil.isAlphanumeric(curso.getCodigoCurso())) {
            throw new IllegalArgumentException("Código do curso deve conter apenas letras e números!");
        }
        
        // Valida coordenador
        if (curso.getCoordenador() != null && 
            StringUtil.containsSpecialCharacters(curso.getCoordenador())) {
            throw new IllegalArgumentException("Nome do coordenador não pode conter caracteres especiais!");
        }
        
        // Verifica se curso existe
        if (findById(curso.getId()) == null) {
            throw new EntityNotFoundException("Curso não encontrado com o ID: " + curso.getId());
        }
        
        // Remove espaços extras
        curso.setNome(StringUtil.trimExtraSpaces(curso.getNome()));
        curso.setCodigoCurso(StringUtil.trimExtraSpaces(curso.getCodigoCurso()));
        if (curso.getCoordenador() != null) {
            curso.setCoordenador(StringUtil.trimExtraSpaces(curso.getCoordenador()));
        }
        
        cursoDao.update(curso);
    }
    
    // Método para buscar todos os cursos cadastrados
    public List<Curso> findAll() throws RuntimeException {
        // Retorna a lista de cursos do banco
        return cursoDao.findAll();
    }
    
    // Método para buscar um curso específico pelo ID
    public Curso findById(Long id) throws RuntimeException {
        // Valida se o ID não é nulo
        if (id == null) {
            throw new RuntimeException ("Curso não cadastrado");
        }
        
        // Retorna o curso encontrado pelo ID
        return cursoDao.findById(id);
    }

    // Método para buscar cursos pelo nome
    public List<Curso> findByNome(String nome) throws RuntimeException {
        // Retorna lista de cursos que correspondem ao nome
        return cursoDao.findByNome(nome);
    } 
    
    // Método para remover um curso do sistema
    public void delete(Curso curso) throws RuntimeException, EntityNotFoundException {
        // Valida se o curso não é nulo
        if (curso == null) {
            throw new RuntimeException ("Dados do Curso nao preenchidos!");
        }
        
        // Verifica se o curso existe antes de deletar
        if (findById(curso.getId()) != null) {
            cursoDao.delete(curso);
        } else {
            throw new EntityNotFoundException("Curso não encontrado com o ID: " + curso.getId());
        }
    }

}
