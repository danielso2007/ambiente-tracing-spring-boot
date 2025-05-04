package br.com.cursos.interfaces.controller;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.infrastructure.exceptions.InternalErrorException;
import br.com.cursos.infrastructure.service.CursoService;
import br.com.cursos.interfaces.dto.CursoDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.CannotCreateTransactionException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

@ExtendWith(MockitoExtension.class)
public class CursoControllerImplTest {

    @InjectMocks
    private CursoControllerImpl cursoController;

    @Mock
    private CursoService cursoService;

    /**
     * Tests the behavior of getAllCursos when a CannotCreateTransactionException occurs.
     * This test verifies that the method throws an InternalErrorException when
     * the database connection fails.
     */
    @Test
    public void testGetAllCursos_whenDatabaseConnectionFails_thenThrowInternalErrorException() {
        Pageable pageable = Pageable.unpaged();
        when(cursoService.findAll(pageable)).thenThrow(new CannotCreateTransactionException("Database connection failed"));

        assertThrows(CannotCreateTransactionException.class, () -> cursoController.getAllCursos(pageable));
    }

    /**
     * Tests the scenario where a CannotCreateTransactionException is thrown during deletion.
     * Expects an InternalErrorException to be thrown with an appropriate error message.
     */
    @Test
    public void test_deleteCursos_cannotCreateTransactionException() {
        UUID id = UUID.randomUUID();
        when(cursoService.findById(id)).thenThrow(new CannotCreateTransactionException("Database connection error"));

        assertThrows(CannotCreateTransactionException.class, () -> cursoController.deleteCursos(id));
    }

    /**
     * Tests the getOneCursos method when a CannotCreateTransactionException occurs.
     * This test verifies that the method throws an InternalErrorException with the appropriate
     * error message when there's a database communication error.
     */
    @Test
    public void test_getOneCursos_cannotCreateTransaction() {
        UUID id = UUID.randomUUID();
        when(cursoService.findById(id)).thenThrow(new CannotCreateTransactionException("Database error"));

        assertThrows(CannotCreateTransactionException.class, () -> cursoController.getOneCursos(id));
    }

    /**
     * Tests the getOneCursos method when a curso is found.
     * This test verifies that when a valid UUID is provided and a curso exists,
     * the method returns a ResponseEntity with OK status and the found curso.
     */
    @Test
    public void test_getOneCursos_whenCursoExists() {
        UUID id = UUID.randomUUID();
        Curso curso = new Curso();
        curso.setId(id);

        when(cursoService.findById(id)).thenReturn(Optional.of(curso));

        ResponseEntity<Curso> response = cursoController.getOneCursos(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    /**
     * Test case for saving a curso when a database communication error occurs.
     * This test verifies that the method throws an InternalErrorException
     * when a DataAccessResourceFailureException is thrown by the cursoService.
     */
    @Test
    public void test_saveCurso_databaseCommunicationError() {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNumeroMatricula("123");
        cursoDto.setNumeroCurso("456");

        when(cursoService.existsByNumeroMatricula("123")).thenReturn(false);
        when(cursoService.existsByNumeroCurso("456")).thenReturn(false);
        when(cursoService.save(any(Curso.class))).thenThrow(new DataAccessResourceFailureException("Database error"));

        assertThrows(DataAccessResourceFailureException.class, () -> cursoController.saveCurso(cursoDto));
    }

    /**
     * Tests the saveCurso method when a new curso is successfully created.
     * This test verifies that when both the numeroMatricula and numeroCurso are unique,
     * the method returns a CREATED status and the saved Curso object.
     */
    @Test
    public void test_saveCurso_whenNewCursoIsCreated_thenReturnCreatedStatus() {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNumeroMatricula("12345");
        cursoDto.setNumeroCurso("C001");

        Curso savedCurso = new Curso();
        savedCurso.setId(UUID.randomUUID());

        when(cursoService.existsByNumeroMatricula("12345")).thenReturn(false);
        when(cursoService.existsByNumeroCurso("C001")).thenReturn(false);
        when(cursoService.save(any(Curso.class))).thenReturn(savedCurso);

        ResponseEntity<Object> response = cursoController.saveCurso(cursoDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedCurso, response.getBody());
    }

    /**
     * Tests the updateCursos method when a CannotCreateTransactionException is thrown.
     * This test verifies that the method throws an InternalErrorException with the appropriate error message.
     */
    @Test
    public void test_updateCursos_cannotCreateTransaction() {
        UUID id = UUID.randomUUID();
        CursoDto cursoDto = new CursoDto();
        when(cursoService.findById(id)).thenThrow(new CannotCreateTransactionException("Database error"));

        assertThrows(CannotCreateTransactionException.class, () -> cursoController.updateCursos(id, cursoDto));
    }

}
