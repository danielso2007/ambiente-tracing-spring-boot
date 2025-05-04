package br.com.cursos.interfaces.controller;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.infrastructure.exceptions.BusinessException;
import br.com.cursos.infrastructure.exceptions.NotFoundException;
import br.com.cursos.infrastructure.service.CursoService;
import br.com.cursos.interfaces.dto.CursoDto;
import io.micrometer.core.annotation.Timed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/curso")
public class CursoControllerImpl implements CursoController {

    private static final Logger logger = LoggerFactory.getLogger(CursoControllerImpl.class);

    final CursoService cursoService;

    public CursoControllerImpl(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Override
    @PostMapping(produces = APPLICATION_JSON_CHARSET_UTF_8)
    public ResponseEntity<Object> saveCurso(CursoDto cursoDto) {
        if (cursoService.existsByNumeroMatricula(cursoDto.getNumeroMatricula())) {
            logger.warn("Novo registro nao inserido, o numero de matricula ja existe!");
            throw new BusinessException("O numero de matricula do curso ja esta em uso!");
        }

        if (cursoService.existsByNumeroCurso(cursoDto.getNumeroCurso())) {
            logger.warn("Novo registro nao inserido, o numero do curso ja existe!");
            throw new BusinessException("O numero do curso ja esta em uso!");
        }

        var cursoModel = new Curso();
        BeanUtils.copyProperties(cursoDto, cursoModel);
        cursoModel.setDataInscricao(LocalDateTime.now(ZoneId.of("UTC")));
        logger.info("Novo registro de curso salvo com sucesso!");
        return ResponseEntity.status(HttpStatus.CREATED).body(cursoService.save(cursoModel));
    }

    @Override
    @Timed(value = "http.server.requests", extraTags = {"uri", "/curso"}, description = "Listagem de cursos", histogram = true)
    @GetMapping(produces = APPLICATION_JSON_CHARSET_UTF_8)
    public ResponseEntity<Page<Curso>> getAllCursos(Pageable pageable) {
        logger.info("Chamando cursoService para buscar todos os registros");
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.findAll(pageable));
    }

    @Override
    @Timed(value = "http.server.requests", extraTags = {"uri", "/curso/{id}"}, description = "Obter curso por id", histogram = true)
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_CHARSET_UTF_8)
    public ResponseEntity<Curso> getOneCursos(UUID id) {
        Optional<Curso> cursoModelOptional = cursoService.findById(id);
        logger.info("Validando por cursoService se o UUID existe");

        if (cursoModelOptional.isEmpty()) {
            logger.warn("Curso não encontrado");
            throw new NotFoundException("Curso não encontrado.");
        }

        logger.info("O registro procurado pelo cliente foi encontrado por cursoService no database");
        return ResponseEntity.status(HttpStatus.OK).body(cursoModelOptional.get());
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCursos(UUID id) {
        logger.info("Chamando cursoService para deletar um registro por UUID");
        Optional<Curso> cursoModelOptional = cursoService.findById(id);
        logger.info("Validando por cursoService se o UUID existe");

        if (cursoModelOptional.isEmpty()) {
            logger.warn("Tentativa de exclusao abortada, UUID informado nao existe!");
            throw new NotFoundException("Curso não encontrado.");
        }

        cursoService.delete(cursoModelOptional.get());
        logger.info("O registro procurado pelo cliente foi encontrado e deletado por cursoService no database");
        return ResponseEntity.status(HttpStatus.OK).body("Curso excluído com sucesso!");
    }

    @Override
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_CHARSET_UTF_8)
    public ResponseEntity<Object> updateCursos(UUID id, CursoDto cursoDto) {
        logger.info("Chamando cursoService para atualizar um registro por UUID");

        Optional<Curso> cursoModelOptional = cursoService.findById(id);
        logger.info("Validando por cursoService se o UUID existe");

        if (!cursoModelOptional.isPresent()) {
            logger.warn("Validacao em cursoService nao encontrou o registro procurado!");
            throw new NotFoundException("Curso não encontrado.");
        }

        var cursoModel = new Curso();
        BeanUtils.copyProperties(cursoDto, cursoModel);
        cursoModel.setId(cursoModelOptional.get().getId());
        cursoModel.setDataInscricao(cursoModelOptional.get().getDataInscricao());
        logger.info("O registro foi atualizado por cursoService no database com sucesso!");
        return ResponseEntity.status(HttpStatus.OK).body(cursoService.save(cursoModel));
    }
}
