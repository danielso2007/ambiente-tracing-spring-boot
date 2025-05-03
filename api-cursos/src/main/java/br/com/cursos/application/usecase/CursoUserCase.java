package br.com.cursos.application.usecase;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.application.domain.repository.CursoRepository;

public class CursoUserCase {

    private final CursoRepository cursoRepository;

    public CursoUserCase(final CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public Curso save(final Curso curso) {
        return cursoRepository.save(curso);
    }

    public boolean existsByNumeroMatricula(final String numeroMatricula) {
        return cursoRepository.existsByNumeroMatricula(numeroMatricula);
    }

    public boolean existsByNumeroCurso(final String numeroCurso) {
        return cursoRepository.existsByNumeroCurso(numeroCurso);
    }

    public Page<Curso> findAll(final Pageable pageable) {
        return cursoRepository.findAll(pageable);
    }

    public Optional<Curso> findById(final UUID idModel) {
        return cursoRepository.findById(idModel);
    }

    public void delete(final Curso curso) {
        cursoRepository.delete(curso);
    }

}
