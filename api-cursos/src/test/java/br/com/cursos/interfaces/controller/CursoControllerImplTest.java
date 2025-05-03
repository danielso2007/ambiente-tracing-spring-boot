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

        assertThrows(InternalErrorException.class, () -> cursoController.getAllCursos(pageable));
    }

    /**
     * Tests the saveCurso method when the course number already exists.
     * Verifies that the method returns a CONFLICT status with the appropriate error message.
     */
    @Test
    public void testSaveCursoWhenCourseNumberExists() {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNumeroMatricula("12345");
        cursoDto.setNumeroCurso("67890");

        when(cursoService.existsByNumeroMatricula("12345")).thenReturn(false);
        when(cursoService.existsByNumeroCurso("67890")).thenReturn(true);

        ResponseEntity<Object> response = cursoController.saveCurso(cursoDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("O numero do curso ja esta em uso!", response.getBody());
    }

    /**
     * Tests the scenario where a CannotCreateTransactionException is thrown during deletion.
     * Expects an InternalErrorException to be thrown with an appropriate error message.
     */
    @Test
    public void test_deleteCursos_cannotCreateTransactionException() {
        UUID id = UUID.randomUUID();
        when(cursoService.findById(id)).thenThrow(new CannotCreateTransactionException("Database connection error"));

        assertThrows(InternalErrorException.class, () -> cursoController.deleteCursos(id));
    }

    /**
     * Tests the scenario where the curso with the given UUID does not exist.
     * Expects a NOT_FOUND response with an appropriate error message.
     */
    @Test
    public void test_deleteCursos_nonExistentId() {
        UUID nonExistentId = UUID.randomUUID();
        when(cursoService.findById(nonExistentId)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = cursoController.deleteCursos(nonExistentId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Curso nao encontrado!", response.getBody());
    }

    /**
     * Test case for deleteCursos method when the course is not found.
     * Verifies that the method returns a NOT_FOUND response with the correct message
     * when the course with the given UUID does not exist.
     */
    @Test
    public void test_deleteCursos_whenCourseNotFound_returnsNotFoundResponse() {
        // Arrange
        UUID nonExistentId = UUID.randomUUID();
        when(cursoService.findById(nonExistentId)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Object> response = cursoController.deleteCursos(nonExistentId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Curso nao encontrado!", response.getBody());
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

        assertThrows(InternalErrorException.class, () -> cursoController.getOneCursos(id));
    }

    /**
     * Tests the getOneCursos method when the requested curso is not found.
     * This test verifies that the method returns a NOT_FOUND status with an appropriate error message
     * when the cursoService cannot find a curso with the given ID.
     */
    @Test
    public void test_getOneCursos_notFound() {
        UUID id = UUID.randomUUID();
        when(cursoService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = cursoController.getOneCursos(id);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Curso não encontrado!", response.getBody());
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

        ResponseEntity<Object> response = cursoController.getOneCursos(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(curso, response.getBody());
    }

    /**
     * Test case for getOneCursos method when the requested curso is not found.
     * It verifies that the method returns a NOT_FOUND status with the correct error message
     * when the cursoService.findById() returns an empty Optional.
     */
    @Test
    public void test_getOneCursos_whenCursoNotFound_returnsNotFound() {
        // Arrange
        UUID id = UUID.randomUUID();
        when(cursoService.findById(id)).thenReturn(Optional.empty());

        // Act
        ResponseEntity<Object> response = cursoController.getOneCursos(id);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Curso não encontrado!", response.getBody());
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

        assertThrows(InternalErrorException.class, () -> cursoController.saveCurso(cursoDto));
    }

    /**
     * Test case for saving a curso with an existing numero curso.
     * This test verifies that the method returns a CONFLICT status when
     * attempting to save a curso with a numero curso that already exists.
     */
    @Test
    public void test_saveCurso_existingNumeroCurso() {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNumeroMatricula("123");
        cursoDto.setNumeroCurso("456");

        when(cursoService.existsByNumeroMatricula("123")).thenReturn(false);
        when(cursoService.existsByNumeroCurso("456")).thenReturn(true);

        ResponseEntity<Object> response = cursoController.saveCurso(cursoDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("O numero do curso ja esta em uso!", response.getBody());
    }

    /**
     * Test case for saving a curso with an existing numero matricula.
     * This test verifies that the method returns a CONFLICT status when
     * attempting to save a curso with a numero matricula that already exists.
     */
    @Test
    public void test_saveCurso_existingNumeroMatricula() {
        CursoDto cursoDto = new CursoDto();
        cursoDto.setNumeroMatricula("123");

        when(cursoService.existsByNumeroMatricula("123")).thenReturn(true);

        ResponseEntity<Object> response = cursoController.saveCurso(cursoDto);

        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("O numero de matricula do curso ja esta em uso!", response.getBody());
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

        assertThrows(InternalErrorException.class, () -> cursoController.updateCursos(id, cursoDto));
    }

    /**
     * Tests the updateCursos method when the curso with the given ID is not found.
     * This test verifies that the method returns a NOT_FOUND status with an appropriate error message.
     */
    @Test
    public void test_updateCursos_notFound() {
        UUID id = UUID.randomUUID();
        CursoDto cursoDto = new CursoDto();
        when(cursoService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = cursoController.updateCursos(id, cursoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Curso nao encontrado!", response.getBody());
    }

    /**
     * Tests the updateCursos method when the curso is not found.
     * Verifies that a NOT_FOUND status with the correct error message is returned.
     */
    @Test
    public void test_updateCursos_whenCursoNotFound_returnsNotFound() {
        UUID id = UUID.randomUUID();
        CursoDto cursoDto = new CursoDto();
        when(cursoService.findById(id)).thenReturn(Optional.empty());

        ResponseEntity<Object> response = cursoController.updateCursos(id, cursoDto);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Curso nao encontrado!", response.getBody());
    }

}
