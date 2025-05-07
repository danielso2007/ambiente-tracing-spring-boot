package br.com.cursos.interfaces.controller;

import org.springframework.http.ResponseEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Cache", description = "Limpar o cache da aplicação")
public interface CacheController {
    
    @Operation(summary = "Limpar o cache")
    public ResponseEntity<String> clearCache();

}
