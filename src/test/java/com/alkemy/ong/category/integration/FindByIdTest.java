package com.alkemy.ong.category.integration;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FindByIdTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Mock
    private MessageSource messageSource;
    private CategoryServiceImpl underTest;
    private Category mockCategory;


    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);
    }

    @Test
    void itShouldCheckIfCategoryExist(){

        mockCategory = new Category();
        mockCategory.setName("Ejemplo");
        mockCategory.setDescription("ejemplo de descripcion");
        mockCategory.setDateCreation(LocalDateTime.now());
        mockCategory.setDateUpdate(LocalDateTime.now());

        Optional<Category> mockOptionalCategory = Optional.of(mockCategory);
        Mockito.when(categoryRepository.findById(1L)).thenReturn(mockOptionalCategory);

        CategoryDto responseCategory;
        responseCategory = underTest.findById(1L);

        verify(categoryRepository).findById(1L);
        assertThat(responseCategory.getName()).isEqualTo(mockCategory.getName());
        assertThat(responseCategory.getDescription()).isEqualTo(mockCategory.getDescription());
        assertThat(responseCategory.getImage()).isEqualTo(mockCategory.getImage());
        assertThat(responseCategory.getDateCreation()).isEqualTo(mockCategory.getDateCreation().toString());
        assertThat(responseCategory.getDateUpdate()).isEqualTo(mockCategory.getDateUpdate().toString());
    }

    @Test
    void itShouldCheckIfCategoryDoesNotExist(){
        Mockito.when(categoryRepository.findById(2L)).thenReturn(Optional.empty());

        assertThatThrownBy(()->underTest.findById(2L))
                .isInstanceOf(NotFoundException.class);
        verify(categoryRepository).findById(2L);
    }



}
