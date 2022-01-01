package com.alkemy.ong.unit.category;

import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateCategoryServiceTest extends BaseCategoryServiceTest{

    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);
    }

    @Test
    void CanCreateCategory() {

        generateMockCategoryUpdate();

        Category mockCategory = new Category();
        categoryMapper.mapDtoToEntityWithDateOfCreation(mockCategory, mockCategoryUpdate);

        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

        Object categoryDto = underTest.createCategory(mockCategoryUpdate);

        verify(categoryRepository).save(any(Category.class));
        assertThat(categoryDto).isExactlyInstanceOf(categoryDto.getClass());

    }

}