/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.dao.CursoIDao;
import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Joice
 */
@Service
public class CursoService implements CursoIService {
    
    // Cria uma instância do DAO para acessar o banco de dados
    @Autowired
    private CursoIDao cursoDao;
    
    // Metodo pra salvar um novo curso no sistema
    @Override
    @Transactional 
    public void save(Curso curso) throws RuntimeException {
        
        validateFields(curso); // Chama o método de validação

        cursoDao.save(curso);
    }

    @Override
    @Transactional 
    public void update(Curso curso) throws RuntimeException, EntityNotFoundException {

        validateFields(curso); // Chama o método de validação
        
        cursoDao.update(curso);
    }
    
    // Método para buscar todos os cursos cadastrados
    @Override
    public List<Curso> findAll() throws RuntimeException {
        // Retorna a lista de cursos do banco
        return cursoDao.findAll();
    }
    
    // Método para buscar um curso específico pelo ID
    @Override
    public Curso findById(Long id) throws RuntimeException {
        // Valida se o ID não é nulo
        if (id == null) {
            throw new RuntimeException ("Curso não cadastrado");
        }
        
        // Retorna o curso encontrado pelo ID
        return cursoDao.findById(id);
    }

    // Método para buscar cursos pelo nome
    @Override
    public List<Curso> findByNome(String nome) throws RuntimeException {
        // Retorna lista de cursos que correspondem ao nome
        return cursoDao.findByNome(nome);
    } 
    
    // Método para remover um curso
    @Override
    @Transactional 
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
    
  // Método de validação
    public void validateFields(Curso curso) {
        // Validação básica
        if (curso == null) {
            throw new RuntimeException("Dados do Curso não preenchidos!");
        }
        
        if (StringUtil.isNullOrEmpty(curso.getNome()) ||
            StringUtil.isNullOrEmpty(curso.getCodigoCurso()) ||
            StringUtil.isNullOrEmpty(curso.getCoordenador())) {
            throw new IllegalArgumentException("Todos os campos (Nome, Código e Coordenador) são obrigatórios.");
        }

        // Valida formato do código
        if (!StringUtil.isAlphanumeric(curso.getCodigoCurso())) {
            throw new IllegalArgumentException("Código do curso deve conter apenas letras e números (sem espaços ou caracteres especiais)!");
        }
        
        // Valida coordenador
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
    }


}
