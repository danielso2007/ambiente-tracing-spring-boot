package br.com.cursos.infrastructure.persistence.repository;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.application.domain.repository.CursoRepository;
import br.com.cursos.infrastructure.persistence.entity.CursoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CursoRepositoryImpl implements CursoRepository {

    private final CursoJpaRepositoryImpl cursoJpaRepository;

    public CursoRepositoryImpl(final CursoJpaRepositoryImpl cursoJpaRepository) {
        this.cursoJpaRepository = cursoJpaRepository;
    }

    @Override
    public boolean existsByNumeroMatricula(final String numeroMatricula) {
        return cursoJpaRepository.existsByNumeroMatricula(numeroMatricula);
    }

    @Override
    public boolean existsByNumeroCurso(final String numeroCurso) {
        return cursoJpaRepository.existsByNumeroCurso(numeroCurso);
    }

    @Override
    public Curso save(final Curso curso) {
        return cursoJpaRepository.save(new CursoEntity().fromDomain(curso)).toDomain();
    }

    @Override
    public Page<Curso> findAll(final Pageable pageable) {
        Page<CursoEntity> page = cursoJpaRepository.findAll(pageable);
        return page.map(CursoEntity::toDomain);
    }

    @Override
    public Optional<Curso> findById(final UUID id) {
        return cursoJpaRepository.findById(id).flatMap(entity -> Optional.ofNullable(entity.toDomain()));
    }

    @Override
    public void delete(final Curso curso) {
        cursoJpaRepository.delete(new CursoEntity().fromDomain(curso));
    }
}
