package br.com.cursos.infrastructure.persistence.repository;

import br.com.cursos.infrastructure.persistence.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CursoJpaRepositoryImpl extends JpaRepository<CursoEntity, UUID> {

    boolean existsByNumeroMatricula(String numeroMatricula);

    boolean existsByNumeroCurso(String numeroCurso);

}
