package com.alkemy.ong.category.unit;

import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateCategoryTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Mock
    private MessageSource messageSource;
    private CategoryServiceImpl underTest;
    private CategoryRequestUpdate mockCategoryUpdate;

    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);
    }

    @Test
    void CanCreateCategory() {

        mockCategoryUpdate = new CategoryRequestUpdate();
        mockCategoryUpdate.setName("Example");
        mockCategoryUpdate.setDescription("Example of description");
        mockCategoryUpdate.setImage("http://image.com/image.jpg");

        Category mockCategory = new Category();
        categoryMapper.mapDtoToEntityWithDateOfCreation(mockCategory, mockCategoryUpdate);

        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

        Object categoryDto = underTest.createCategory(mockCategoryUpdate);

        verify(categoryRepository).save(any(Category.class));
        assertThat(categoryDto).isExactlyInstanceOf(categoryDto.getClass());

    }

    @Test
    void checkIfArgumentNotValid(){
        mockCategoryUpdate = new CategoryRequestUpdate();
        mockCategoryUpdate.setName(null);
        mockCategoryUpdate.setDescription("Example of description");
        mockCategoryUpdate.setImage("http://image.com/image.jpg");

        Category mockCategory = new Category();
        categoryMapper.mapDtoToEntityWithDateOfCreation(mockCategory, mockCategoryUpdate);

        Mockito.when(categoryRepository.save(any(Category.class))).thenReturn(mockCategory);

        assertThatThrownBy(()->underTest.createCategory(mockCategoryUpdate))
                .isInstanceOf(MethodArgumentNotValidException.class);
    }
}