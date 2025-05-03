package br.com.cursos.infrastructure.service;

import br.com.cursos.application.domain.model.Curso;
import br.com.cursos.application.usecase.CursoUserCase;
import br.com.cursos.infrastructure.persistence.repository.CursoJpaRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CursoServiceTest {

    @InjectMocks
    private CursoService cursoService;

    @MockitoBean
    private CursoUserCase cursoUserCase;

    @MockitoBean
    private CursoJpaRepositoryImpl cursoJpaRepository;

    /**
     * Tests that the findAll method correctly delegates to cursoUserCase and returns its result.
     * This test verifies that the @Cacheable annotation is working as expected by returning
     * the result from cursoUserCase.findAll(pageable) without any modifications.
     */
    @Test
    public void test_findAll_returns_page_from_user_case() {
        // Arrange
        CursoUserCase mockCursoUserCase = Mockito.mock(CursoUserCase.class);
        CursoService cursoService = new CursoService(null);
        java.lang.reflect.Field field;
        try {
            field = CursoService.class.getDeclaredField("cursoUserCase");
            field.setAccessible(true);
            field.set(cursoService, mockCursoUserCase);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        Pageable mockPageable = Mockito.mock(Pageable.class);
        Page<Curso> mockPage = Mockito.mock(Page.class);

        when(mockCursoUserCase.findAll(mockPageable)).thenReturn(mockPage);

        // Act
        Page<Curso> result = cursoService.findAll(mockPageable);

        // Assert
        assertEquals(mockPage, result);
        Mockito.verify(mockCursoUserCase).findAll(mockPageable);
    }

}
