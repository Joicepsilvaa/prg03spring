/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.ifba.curso.service;

import br.com.ifba.curso.entity.Curso;
import br.com.ifba.infrastructure.util.StringUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.ifba.curso.repository.CursoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Joice
 */
@Service
@RequiredArgsConstructor
@Slf4j 
public class CursoService implements CursoIService {

    @Autowired
    private final CursoRepository cursoRepository;
    
    private static final Logger log = LoggerFactory.
                              getLogger(CursoService.class);

    @Override
    @Transactional
    public void save(Curso curso) {
        validateFields(curso);
        log.info("[CursoService] Iniciando salvamento de curso...");
        cursoRepository.save(curso);       
        log.info("[CursoService] Curso salvo com sucesso.");
    }

    @Override
    @Transactional
    public void update(Curso curso) {
        validateFields(curso);

        if (!cursoRepository.existsById(curso.getId())) {
            throw new EntityNotFoundException("Curso não encontrado com ID: " + curso.getId());
        }

        cursoRepository.save(curso);
        log.info("Curso" + curso + "teve suas informações alteradas");
    }

    @Override
    public List<Curso> findAll() {
        log.info("Listando cursos");
        return cursoRepository.findAll();
    }

    @Override
    public Curso findById(Long id) {
        if (id == null) {
            throw new RuntimeException("Curso não cadastrado");
        }

        return cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));
    }

    @Override
    public List<Curso> findByNome(String nome) {
        if (StringUtil.isNullOrEmpty(nome)) {
            throw new IllegalArgumentException("O nome não pode estar vazio.");
        }

        return cursoRepository.findByNomeContainingIgnoreCase(nome.trim());
}
    @Override
    @Transactional
    public void delete(Curso curso) {
        if (curso == null || curso.getId() == null) {
            throw new RuntimeException("Dados do Curso não preenchidos!");
        }

        Curso existente = cursoRepository.findById(curso.getId())
            .orElseThrow(() -> new EntityNotFoundException("Curso não encontrado com o ID: " + curso.getId()));

        cursoRepository.delete(existente);
        log.info("Curso" + curso + "foi excluído");
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
