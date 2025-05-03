package br.com.cursos.interfaces.controller;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.interfaces.dto.CursoDto;
import br.com.cursos.interfaces.handlers.ProblemDetail;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
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
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.UUID;

@RequestMapping("/curso")
@Tag(name = "Curso", description = "Operações com cursos")
public interface CursoController {
    String ERRO_MOMENTANEO_POR_FAVOR_MSG = "Erro momentaneo, por favor tente mais tarde...";
    String ERRO_DE_COMUMICACAO_COM_O_DATABASE = "Erro de comumicação com o database";
    String SUCESSO = "200";
    String BAD_REQUEST = "400";
    String NOT_FOUND = "404";
    String BAD_GATEWAY = "502";
    String APPLICATION_JSON_CHARSET_UTF_8 = "application/json;charset=utf-8";
    String BAD_GATEWAY_MSG = "Bad Gateway.";
    String CURSO_NOT_FOUND_MSG = "Curso not found.";
    String CONTENT_TYPE_JSON = "application/json";
    String INVALID_ID_SUPPLIED_MSG = "Invalid id supplied.";

    @Operation(summary = "Cadastra um curso.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = SUCESSO, description = "Curso salvo com sucesso.",
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = CursoDto.class)) }),
        @ApiResponse(responseCode = BAD_REQUEST, description = INVALID_ID_SUPPLIED_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = NOT_FOUND, description = CURSO_NOT_FOUND_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = BAD_GATEWAY, description = BAD_GATEWAY_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    @PostMapping(produces = APPLICATION_JSON_CHARSET_UTF_8)
    ResponseEntity<Object> saveCurso(@RequestBody @Valid CursoDto cursoDto);

    @Operation(summary = "Obter todos os cursos paginado.",
        parameters = {
            @Parameter(name = "page", description = "Número da página (0-base)", example = "0"),
            @Parameter(name = "size", description = "Tamanho da página", example = "20"),
            @Parameter(name = "sort", description = "Ordenação: campo,direção", example = "dataInscricao,ASC")
        })
    @ApiResponses(value = {
        @ApiResponse(responseCode = SUCESSO, description = "A lista de cursos.",
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = Page.class)) }),
        @ApiResponse(responseCode = BAD_REQUEST, description = INVALID_ID_SUPPLIED_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = NOT_FOUND, description = CURSO_NOT_FOUND_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = BAD_GATEWAY, description = BAD_GATEWAY_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    @GetMapping(produces = APPLICATION_JSON_CHARSET_UTF_8)
    ResponseEntity<Page<Curso>> getAllCursos(@ParameterObject @PageableDefault(page = 0, size = 10, sort = "dataInscricao", direction = Sort.Direction.ASC) Pageable pageable);

    @Operation(summary = "Obter um curso por ID informado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = SUCESSO, description = "Curso encontrado com sucesso.",
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = CursoDto.class)) }),
        @ApiResponse(responseCode = BAD_REQUEST, description = INVALID_ID_SUPPLIED_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = NOT_FOUND, description = CURSO_NOT_FOUND_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = BAD_GATEWAY, description = BAD_GATEWAY_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_CHARSET_UTF_8)
    ResponseEntity<Object> getOneCursos(@PathVariable(value = "id") UUID id);

    @Operation(summary = "Deletar um curso por ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = SUCESSO, description = "Curso deletado com sucesso."),
        @ApiResponse(responseCode = BAD_REQUEST, description = INVALID_ID_SUPPLIED_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = NOT_FOUND, description = CURSO_NOT_FOUND_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = BAD_GATEWAY, description = BAD_GATEWAY_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Object> deleteCursos(@PathVariable(value = "id") UUID id);

    @Operation(summary = "Atualizar um curso.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = SUCESSO, description = "Curso atualizado com sucesso.",
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = CursoDto.class)) }),
        @ApiResponse(responseCode = BAD_REQUEST, description = INVALID_ID_SUPPLIED_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = NOT_FOUND, description = CURSO_NOT_FOUND_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) }),
        @ApiResponse(responseCode = BAD_GATEWAY, description = BAD_GATEWAY_MSG,
                content = { @Content(mediaType = CONTENT_TYPE_JSON, schema = @Schema(implementation = ProblemDetail.class)) })
    })
    @PutMapping(value = "/{id}", produces = APPLICATION_JSON_CHARSET_UTF_8)
    ResponseEntity<Object> updateCursos(@PathVariable(value = "id") UUID id, @RequestBody @Valid CursoDto cursoDto);
}
