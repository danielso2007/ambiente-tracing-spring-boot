package br.com.cursos.infrastructure.service;

import java.util.Optional;
import java.util.UUID;
import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.infrastructure.persistence.repository.CursoRepositoryImpl;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import br.com.cursos.application.usecase.CursoUserCase;
import jakarta.transaction.Transactional;

@Service
public class CursoService {

    private final CursoUserCase cursoUserCase;

    public CursoService(CursoRepositoryImpl cursoRepositoryImpl) {
        this.cursoUserCase = new CursoUserCase(cursoRepositoryImpl);
    }

    @Transactional
    @CacheEvict(value = {"listaDeCursos", "nomeCurso"}, allEntries = true)
    public Curso save(final Curso cursoModel) {
        return cursoUserCase.save(cursoModel);
    }

    public boolean existsByNumeroMatricula(final String numeroMatricula) {
        return cursoUserCase.existsByNumeroMatricula(numeroMatricula);
    }

    public boolean existsByNumeroCurso(final String numeroCurso) {
        return cursoUserCase.existsByNumeroCurso(numeroCurso);
    }

    @Cacheable(value = "listaDeCursos")
    public Page<Curso> findAll(final Pageable pageable) {
        return cursoUserCase.findAll(pageable);
    }

    @Cacheable(value = "nomeCurso")
    public Optional<Curso> findById(final UUID idModel) {
        return cursoUserCase.findById(idModel);
    }

    @Transactional
    @CacheEvict(value = {"listaDeCursos", "nomeCurso"}, allEntries = true)
    public void delete(final Curso cursoModel) {
        cursoUserCase.delete(cursoModel);
    }

}
