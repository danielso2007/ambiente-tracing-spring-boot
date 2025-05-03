package br.com.cursos.application.usecase;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.application.domain.repository.CursoRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoUserCaseTest {

    @Mock
    private CursoRepository cursoRepository;

    @InjectMocks
    private CursoUserCase cursoUserCase;

    /**
     * Tests the constructor of CursoUserCase to ensure it correctly initializes
     * the cursoRepository field with the provided repository instance.
     */
    @Test
    public void testCursoUserCaseConstructorInitialization() {
        // Arrange
        CursoRepository mockRepository = mock(CursoRepository.class);

        // Act
        CursoUserCase cursoUserCase = new CursoUserCase(mockRepository);

        // Assert
        // Note: We cannot directly assert the private field, so this test only verifies
        // that the constructor executes without throwing an exception.
        // In a real-world scenario, we would test the behavior of methods that use the repository.
    }

    /**
     * Tests that the delete method correctly calls the repository's delete method with the provided Curso object.
     */
    @Test
    public void testDeleteCallsRepositoryDelete() {
        // Arrange
        CursoRepository mockRepository = Mockito.mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(mockRepository);
        Curso curso = new Curso();

        // Act
        cursoUserCase.delete(curso);

        // Assert
        Mockito.verify(mockRepository).delete(curso);
    }

    /**
     * Tests that existsByNumeroCurso returns true when the curso number exists.
     * This test verifies that the method correctly delegates to the repository
     * and returns the expected result for an existing curso number.
     */
    @Test
    public void testExistsByNumeroCursoReturnsTrue() {
        // Arrange
        CursoRepository cursoRepository = Mockito.mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(cursoRepository);
        String numeroCurso = "123456";
        when(cursoRepository.existsByNumeroCurso(numeroCurso)).thenReturn(true);

        // Act
        boolean result = cursoUserCase.existsByNumeroCurso(numeroCurso);

        // Assert
        assertTrue(result);
    }

    /**
     * Tests that existsByNumeroMatricula returns the result from cursoRepository.
     * This test verifies that the method correctly delegates to the repository
     * and returns its result without modification.
     */
    @Test
    public void testExistsByNumeroMatriculaDelegatesToRepository() {
        // Arrange
        CursoRepository cursoRepository = Mockito.mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(cursoRepository);
        String numeroMatricula = "12345";
        when(cursoRepository.existsByNumeroMatricula(numeroMatricula)).thenReturn(true);

        // Act
        boolean result = cursoUserCase.existsByNumeroMatricula(numeroMatricula);

        // Assert
        assertTrue(result);
        verify(cursoRepository).existsByNumeroMatricula(numeroMatricula);
    }

    /**
     * Test case for findAll method of CursoUserCase
     * Verifies that the method correctly returns the Page of Cursos from the repository
     */
    @Test
    public void testFindAllReturnsPageOfCursos() {
        // Arrange
        Pageable pageable = Pageable.unpaged();
        List<Curso> cursos = new ArrayList<>();
        cursos.add(new Curso());
        Page<Curso> expectedPage = new PageImpl<>(cursos);
        when(cursoRepository.findAll(pageable)).thenReturn(expectedPage);

        // Act
        Page<Curso> result = cursoUserCase.findAll(pageable);

        // Assert
        assertEquals(expectedPage, result);
    }

    /**
     * Tests the findAll method with a null Pageable object.
     * This test verifies that the method handles a null Pageable parameter correctly
     * by throwing a NullPointerException.
     */
    @Test
    public void testFindAllWithNullPageable() {
        CursoRepository cursoRepository = mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(cursoRepository);

        when(cursoRepository.findAll((Pageable) null)).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> cursoUserCase.findAll(null));
    }

    /**
     * Test case for saving a Curso object using the save method of CursoUserCase.
     * This test verifies that the save method correctly delegates to the cursoRepository
     * and returns the saved Curso object.
     */
    @Test
    public void testSaveCursoSuccessfully() {
        // Arrange
        CursoUserCase cursoUserCase = new CursoUserCase(cursoRepository);
        Curso curso = new Curso();
        when(cursoRepository.save(curso)).thenReturn(curso);

        // Act
        Curso savedCurso = cursoUserCase.save(curso);

        // Assert
        assertNotNull(savedCurso);
        verify(cursoRepository, times(1)).save(curso);
        assertEquals(curso, savedCurso);
    }

    /**
     * Tests that saving a null Curso object results in a NullPointerException.
     * This test verifies that the save method properly handles null input by
     * throwing a NullPointerException, which is the expected behavior when
     * passing null to the repository's save method.
     */
    @Test
    public void testSaveNullCurso() {
        CursoRepository mockRepository = org.mockito.Mockito.mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(mockRepository);

        org.mockito.Mockito.when(mockRepository.save(null)).thenThrow(NullPointerException.class);

        org.junit.jupiter.api.Assertions.assertThrows(NullPointerException.class, () -> {
            cursoUserCase.save(null);
        });
    }

    /**
     * Tests the behavior of existsByNumeroCurso method when given a null input.
     * This test verifies that the method correctly handles null values by delegating
     * the null check to the underlying repository method.
     */
    @Test
    public void test_existsByNumeroCurso_with_null_input() {
        CursoRepository cursoRepository = Mockito.mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(cursoRepository);

        Mockito.when(cursoRepository.existsByNumeroCurso(null)).thenReturn(false);

        boolean result = cursoUserCase.existsByNumeroCurso(null);

        org.junit.jupiter.api.Assertions.assertFalse(result);
        Mockito.verify(cursoRepository).existsByNumeroCurso(null);
    }

    /**
     * Tests the findById method with a non-existent UUID.
     * This test verifies that the method returns an empty Optional
     * when the repository does not contain a Curso with the given ID.
     */
    @Test
    public void test_findById_nonExistentId() {
        CursoRepository mockRepository = mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(mockRepository);

        UUID nonExistentId = UUID.randomUUID();
        when(mockRepository.findById(nonExistentId)).thenReturn(Optional.empty());

        Optional<Curso> result = cursoUserCase.findById(nonExistentId);

        assertTrue(result.isEmpty());
    }

    /**
     * Tests that findById method correctly returns the Optional<Curso> from the repository
     * when given a valid UUID.
     */
    @Test
    public void test_findById_returnsOptionalCursoFromRepository() {
        // Arrange
        CursoRepository cursoRepository = Mockito.mock(CursoRepository.class);
        CursoUserCase cursoUserCase = new CursoUserCase(cursoRepository);
        UUID testId = UUID.randomUUID();
        Curso expectedCurso = new Curso();
        Mockito.when(cursoRepository.findById(testId)).thenReturn(Optional.of(expectedCurso));

        // Act
        Optional<Curso> result = cursoUserCase.findById(testId);

        // Assert
        Mockito.verify(cursoRepository).findById(testId);
        assert result.isPresent();
        assert result.get() == expectedCurso;
    }

}
