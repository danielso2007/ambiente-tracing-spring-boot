package br.com.cursos.interfaces.controller;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.interfaces.dto.CursoDto;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.UUID;

public interface CursoController {
    String ERRO_MOMENTANEO_POR_FAVOR_MSG = "Erro momentaneo, por favor tente mais tarde...";
    String ERRO_DE_COMUMICACAO_COM_O_DATABASE = "Erro de comumicação com o database";

    @PostMapping
    ResponseEntity<Object> saveCurso(@RequestBody @Valid CursoDto cursoDto);

    @GetMapping
    ResponseEntity<Page<Curso>> getAllCursos(@PageableDefault(page = 0, size = 10, sort = "dataInscricao", direction = Sort.Direction.ASC) Pageable pageable);

    @GetMapping("/{id}")
    ResponseEntity<Object> getOneCursos(@PathVariable(value = "id") UUID id);

    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteCursos(@PathVariable(value = "id") UUID id);

    @PutMapping("/{id}")
    ResponseEntity<Object> updateCursos(@PathVariable(value = "id") UUID id, @RequestBody @Valid CursoDto cursoDto);
}
