package br.com.cursos.service;

import br.com.cursos.model.CursoModel;
import br.com.cursos.repository.CursoRepository;
import jakarta.transaction.Transactional;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
@SuppressWarnings("PMD.UnnecessaryAnnotationValueElement")
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(final CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    @Transactional
    @CacheEvict(value = {"listaDeCursos", "nomeCurso"}, allEntries = true)
    public CursoModel save(final CursoModel cursoModel) {
        return cursoRepository.save(cursoModel);
    }

    public boolean existsByNumeroMatricula(final String numeroMatricula) {
        return cursoRepository.existsByNumeroMatricula(numeroMatricula);
    }

    public boolean existsByNumeroCurso(final String numeroCurso) {
        return cursoRepository.existsByNumeroCurso(numeroCurso);
    }

    @Cacheable(value = "listaDeCursos")
    public Page<CursoModel> findAll(final Pageable pageable) {
        return cursoRepository.findAll(pageable);
    }

    @Cacheable(value = "nomeCurso")
    public Optional<CursoModel> findById(final UUID idModel) {
        return cursoRepository.findById(idModel);
    }

    @Transactional
    @CacheEvict(value = {"listaDeCursos", "nomeCurso"}, allEntries = true)
    public void delete(final CursoModel cursoModel) {
        cursoRepository.delete(cursoModel);

    }
}
