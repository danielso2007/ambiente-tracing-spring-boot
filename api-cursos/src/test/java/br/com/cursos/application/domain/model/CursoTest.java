package br.com.cursos.application.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;
import java.time.LocalDateTime;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class CursoTest {

    /**
     * Test that getNomeProfessor returns null when nomeProfessor is not set.
     * This tests the edge case where the nomeProfessor field has not been initialized.
     */
    @Test
    public void testGetNomeProfessorWhenNotSet() {
        Curso curso = new Curso();
        assertNull(curso.getNomeProfessor());
    }

    /**
     * Test setting categoria curso with empty string.
     * This test verifies that the setCategoriaCurso method accepts an empty string without throwing an exception.
     */
    @Test
    public void testSetCategoriaCursoWithEmptyString() {
        Curso curso = new Curso();
        curso.setCategoriaCurso("");
        // No assertion needed as we're just verifying that no exception is thrown
    }

    /**
     * Test setting categoria curso with null value.
     * This test verifies that the setCategoriaCurso method accepts null input without throwing an exception.
     */
    @Test
    public void testSetCategoriaCursoWithNullValue() {
        Curso curso = new Curso();
        curso.setCategoriaCurso(null);
        // No assertion needed as we're just verifying that no exception is thrown
    }

    /**
     * Test setting the professor name to an empty string.
     * This test verifies that the setNomeProfessor method accepts an empty string
     * without throwing an exception, as there's no explicit check for empty strings
     * in the method implementation.
     */
    @Test
    public void testSetNomeProfessorWithEmptyString() {
        Curso curso = new Curso();
        curso.setNomeProfessor("");
        // No assertion needed as we're just verifying that no exception is thrown
    }

    /**
     * Test setting the professor name to null.
     * This test verifies that the setNomeProfessor method accepts null input
     * without throwing an exception, as there's no explicit null check in the
     * method implementation.
     */
    @Test
    public void testSetNomeProfessorWithNull() {
        Curso curso = new Curso();
        curso.setNomeProfessor(null);
        // No assertion needed as we're just verifying that no exception is thrown
    }

    /**
     * Tests setting the periodoCurso to null.
     * This is a negative test case that verifies the behavior of setPeriodoCurso
     * when passed a null value. The current implementation does not explicitly
     * handle null inputs, so this test simply verifies that no exception is thrown.
     */
    @Test
    public void testSetPeriodoCursoWithNull() {
        Curso curso = new Curso();
        curso.setPeriodoCurso(null);
        // No assertion needed as we're just verifying that no exception is thrown
    }

    /**
     * Tests the constructor of the Curso class by creating a new Curso object
     * with valid parameters and verifying that all fields are correctly initialized.
     */
    @Test
    public void test_Curso_ConstructorInitializesFieldsCorrectly() {
        UUID id = UUID.randomUUID();
        String numeroMatricula = "12345";
        String numeroCurso = "CS101";
        String nomeCurso = "Introduction to Computer Science";
        String categoriaCurso = "Computer Science";
        String preRequisito = "None";
        String nomeProfessor = "Dr. Smith";
        String periodoCurso = "Fall 2023";
        LocalDateTime dataInscricao = LocalDateTime.now();

        Curso curso = new Curso(id, numeroMatricula, numeroCurso, nomeCurso, categoriaCurso,
                preRequisito, nomeProfessor, periodoCurso, dataInscricao);

        assertEquals(id, curso.getId());
        assertEquals(numeroMatricula, curso.getNumeroMatricula());
        assertEquals(numeroCurso, curso.getNumeroCurso());
        assertEquals(nomeCurso, curso.getNomeCurso());
        assertEquals(categoriaCurso, curso.getCategoriaCurso());
        assertEquals(preRequisito, curso.getPreRequisito());
        assertEquals(nomeProfessor, curso.getNomeProfessor());
        assertEquals(periodoCurso, curso.getPeriodoCurso());
        assertEquals(dataInscricao, curso.getDataInscricao());
    }

    /**
     * Tests that getCategoriaCurso() returns the correct category of the course.
     * This test verifies that the method returns the value of the categoriaCurso field.
     */
    @Test
    public void test_getCategoriaCurso_returnsCorrectCategory() {
        String expectedCategory = "Programming";
        Curso curso = new Curso();
        curso.setCategoriaCurso(expectedCategory);

        String actualCategory = curso.getCategoriaCurso();

        assertEquals(expectedCategory, actualCategory, "getCategoriaCurso should return the correct category");
    }

    /**
     * Tests that getDataInscricao() returns the correct dataInscricao value
     * that was set during object initialization.
     */
    @Test
    public void test_getDataInscricao_returnsCorrectValue() {
        LocalDateTime expectedDate = LocalDateTime.of(2023, 1, 1, 0, 0);
        Curso curso = new Curso();
        curso.setDataInscricao(expectedDate);

        LocalDateTime actualDate = curso.getDataInscricao();

        assertEquals(expectedDate, actualDate);
    }

    /**
     * Tests that getId() returns the correct UUID that was set during object creation.
     */
    @Test
    public void test_getId_returnsCorrectId() {
        UUID expectedId = UUID.randomUUID();
        Curso curso = new Curso(expectedId, null, null, null, null, null, null, null, null);

        UUID actualId = curso.getId();

        assertEquals(expectedId, actualId);
    }

    /**
     * Test case for getNomeProfessor method
     * Verifies that the method correctly returns the professor's name
     */
    @Test
    public void test_getNomeProfessor_returnsCorrectName() {
        Curso curso = new Curso();
        String expectedName = "John Doe";
        curso.setNomeProfessor(expectedName);
        assertEquals(expectedName, curso.getNomeProfessor());
    }

    /**
     * Test case for getNumeroCurso method when numeroCurso is null.
     * This test verifies that the method returns null when the numeroCurso field is not set.
     */
    @Test
    public void test_getNumeroCurso_whenNumeroCursoIsNull() {
        Curso curso = new Curso();
        assertNull(curso.getNumeroCurso());
    }

    /**
     * Test case for getNumeroMatricula method of Curso class.
     * This test verifies that the method correctly returns the numeroMatricula value.
     */
    @Test
    public void test_getNumeroMatricula_ReturnsCorrectValue() {
        String expectedNumeroMatricula = "12345";
        Curso curso = new Curso();
        curso.setNumeroMatricula(expectedNumeroMatricula);

        String actualNumeroMatricula = curso.getNumeroMatricula();

        assertEquals(expectedNumeroMatricula, actualNumeroMatricula, "getNumeroMatricula should return the correct numeroMatricula value");
    }

    /**
     * Test getNumeroMatricula when numeroMatricula is null
     * This test verifies that getNumeroMatricula correctly returns null when the numeroMatricula field is not set
     */
    @Test
    public void test_getNumeroMatricula_returnsNull_whenNotSet() {
        Curso curso = new Curso();
        assertNull(curso.getNumeroMatricula());
    }

    /**
     * Test case for getPreRequisito method
     * <p>
     * This test verifies that the getPreRequisito method correctly returns the preRequisito value
     * set during object initialization.
     */
    @Test
    public void test_getPreRequisito_returnsCorrectValue() {
        String expectedPreRequisito = "Sample Prerequisite";
        Curso curso = new Curso();
        curso.setPreRequisito(expectedPreRequisito);

        String actualPreRequisito = curso.getPreRequisito();

        assertEquals(expectedPreRequisito, actualPreRequisito, "getPreRequisito should return the correct preRequisito value");
    }

    /**
     * Test case for setCategoriaCurso method
     * Verifies that the categoriaCurso is correctly set and retrieved
     */
    @Test
    public void test_setCategoriaCurso_1() {
        Curso curso = new Curso();
        String expectedCategoria = "Tecnologia";
        curso.setCategoriaCurso(expectedCategoria);
        assertEquals(expectedCategoria, curso.getCategoriaCurso());
    }

    /**
     * Tests the setDataInscricao method of the Curso class.
     * This test verifies that the dataInscricao field is correctly set
     * when the setDataInscricao method is called with a valid LocalDateTime object.
     */
    @Test
    public void test_setDataInscricao_1() {
        Curso curso = new Curso();
        LocalDateTime testDate = LocalDateTime.of(2023, 5, 15, 10, 30);
        curso.setDataInscricao(testDate);
        assertEquals(testDate, curso.getDataInscricao(), "The dataInscricao should be set to the provided date");
    }

    /**
     * Tests that the setId method correctly sets the id of a Curso object.
     * This test creates a new Curso object, sets its id using setId method,
     * and then verifies that getId returns the same id that was set.
     */
    @Test
    public void test_setId_setsIdCorrectly() {
        Curso curso = new Curso();
        UUID id = UUID.randomUUID();
        curso.setId(id);
        assertEquals(id, curso.getId());
    }

    /**
     * Test case for setNomeCurso method
     * Verifies that the nomeCurso field is correctly set when calling setNomeCurso
     */
    @Test
    public void test_setNomeCurso_setsCorrectValue() {
        Curso curso = new Curso();
        String expectedNomeCurso = "Introduction to Computer Science";

        curso.setNomeCurso(expectedNomeCurso);

        assertEquals(expectedNomeCurso, curso.getNomeCurso());
    }

    /**
     * Test case for setNomeProfessor method
     * Verifies that the nomeProfessor field is correctly set
     */
    @Test
    public void test_setNomeProfessor_1() {
        Curso curso = new Curso();
        String expectedNomeProfessor = "John Doe";
        curso.setNomeProfessor(expectedNomeProfessor);
        assertEquals(expectedNomeProfessor, curso.getNomeProfessor());
    }

    /**
     * Tests that setNumeroCurso correctly sets the numeroCurso field.
     */
    @Test
    public void test_setNumeroCurso_setsNumeroCursoCorrectly() {
        Curso curso = new Curso();
        String expectedNumeroCurso = "CS101";

        curso.setNumeroCurso(expectedNumeroCurso);

        assertEquals(expectedNumeroCurso, curso.getNumeroCurso());
    }

    /**
     * This test verifies that the setNumeroCurso method accepts a null value without throwing an exception.
     * While it may not be an ideal scenario, the current implementation does not explicitly handle null inputs.
     */
    @Test
    public void test_setNumeroCurso_withNullInput() {
        Curso curso = new Curso();
        curso.setNumeroCurso(null);
        // The test passes if no exception is thrown
    }

    /**
     * Tests that setNumeroMatricula correctly sets the numeroMatricula field.
     * This test verifies that when a new numeroMatricula is set, it can be retrieved correctly.
     */
    @Test
    public void test_setNumeroMatricula_setsValueCorrectly() {
        Curso curso = new Curso();
        String expectedNumeroMatricula = "12345";
        curso.setNumeroMatricula(expectedNumeroMatricula);
        assertEquals(expectedNumeroMatricula, curso.getNumeroMatricula());
    }

    /**
     * Tests that setPeriodoCurso correctly sets the periodo curso value
     * and that getPeriodoCurso returns the updated value.
     */
    @Test
    public void test_setPeriodoCurso_setsAndRetrievesCorrectly() {
        Curso curso = new Curso();
        String expectedPeriodo = "2023-2";

        curso.setPeriodoCurso(expectedPeriodo);

        assertEquals(expectedPeriodo, curso.getPeriodoCurso());
    }

    /**
     * Tests that the setPreRequisito method correctly sets the preRequisito field.
     * This test verifies that when a non-null string is passed to setPreRequisito,
     * the getPreRequisito method returns the same string.
     */
    @Test
    public void test_setPreRequisito_setsValueCorrectly() {
        Curso curso = new Curso();
        String expectedPreRequisito = "Mathematics 101";

        curso.setPreRequisito(expectedPreRequisito);

        assertEquals(expectedPreRequisito, curso.getPreRequisito());
    }

}
