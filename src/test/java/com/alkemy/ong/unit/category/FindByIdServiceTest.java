package com.alkemy.ong.unit.category;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FindByIdServiceTest extends BaseCategoryServiceTest{


    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);
    }

    @Test
    void itShouldCheckIfCategoryExist(){

        generateMockCategory();

        Optional<Category> mockOptionalCategory = Optional.of(mockCategory);
        Mockito.when(categoryRepository.findById(ID_CATEGORY)).thenReturn(mockOptionalCategory);

        CategoryDto responseCategory;
        responseCategory = underTest.findById(ID_CATEGORY);

        verify(categoryRepository).findById(ID_CATEGORY);
        assertThat(responseCategory.getName()).isEqualTo(mockCategory.getName());
        assertThat(responseCategory.getDescription()).isEqualTo(mockCategory.getDescription());
        assertThat(responseCategory.getImage()).isEqualTo(mockCategory.getImage());
        assertThat(responseCategory.getDateCreation()).isEqualTo(mockCategory.getDateCreation().toString());
        assertThat(responseCategory.getDateUpdate()).isEqualTo(mockCategory.getDateUpdate().toString());
    }

    @Test
    void itShouldCheckIfCategoryDoesNotExist(){
        Mockito.when(categoryRepository.findById(ID_CATEGORY_NOTFOUND)).thenReturn(Optional.empty());

        assertThatThrownBy(()->underTest.findById(ID_CATEGORY_NOTFOUND))
                .isInstanceOf(NotFoundException.class);
        verify(categoryRepository).findById(ID_CATEGORY_NOTFOUND);
    }



}
