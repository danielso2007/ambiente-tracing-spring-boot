package br.com.cursos.application.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import br.com.cursos.application.domain.model.Curso;
import java.util.Optional;
import java.util.UUID;

public interface CursoRepository {

    boolean existsByNumeroMatricula(String numeroMatricula);

    boolean existsByNumeroCurso(String numeroCurso);

    Curso save(Curso curso);

    Page<Curso> findAll(Pageable pageable);

    Optional<Curso> findById(UUID id);

    void delete(Curso curso);
}
