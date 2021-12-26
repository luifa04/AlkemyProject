package com.alkemy.ong.category;


import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UpdateCategoryTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Mock
    private MessageSource messageSource;
    private CategoryServiceImpl underTest;
    private CategoryRequestUpdate mockCategoryUpdate;
    private Category mockCategory;

    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);

        mockCategoryUpdate = new CategoryRequestUpdate();
        mockCategoryUpdate.setName("Example");
        mockCategoryUpdate.setDescription("Example of description");
        mockCategoryUpdate.setImage("http://image.com/image.jpg");

        mockCategory = new Category();
        mockCategory.setId(1L);
        categoryMapper.mapDtoToEntityWithDateOfCreation(mockCategory, mockCategoryUpdate);
    }

    @Test
    void canUpdateCategory(){

        Optional<Category> mockOptionalCategory = Optional.of(mockCategory);
        given(categoryRepository.findById(1L)).willReturn(mockOptionalCategory);
        given(categoryRepository.save(any(Category.class))).willReturn(mockCategory);

        Object categoryDto = underTest.updateCategory(mockCategoryUpdate, 1L);

        verify(categoryRepository).save(any(Category.class));
        assertThat(categoryDto).isExactlyInstanceOf(categoryDto.getClass());

    }

    @Test
    void checkIfCategoryDoesNotExist(){

        given(categoryRepository.findById(2L)).willReturn(Optional.empty());

        Object categoryDto = underTest.updateCategory(mockCategoryUpdate, 2L);

        verify(categoryRepository).findById(2L);
        verify(categoryRepository,never()).save(any(Category.class));
        assertThat(categoryDto).isEqualTo(null);
    }
}
