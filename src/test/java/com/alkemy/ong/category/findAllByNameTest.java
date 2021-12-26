package com.alkemy.ong.category;

import com.alkemy.ong.dto.CategoryByNameDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class findAllByNameTest {
    @Mock
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Mock
    private MessageSource messageSource;
    private CategoryServiceImpl underTest;
    private Category mockCategory;
    private List<Category> mockList;


    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);
    }

    @Test
    void canFindAllByName(){

        mockCategory = new Category();
        mockCategory.setId(1L);
        mockCategory.setName("Example");
        mockCategory.setDescription("Example of description");
        mockCategory.setImage("http://image.com/image.jpg");
        mockCategory.setDateCreation(LocalDateTime.now());

        mockList = List.of(mockCategory,mockCategory);
        given(categoryRepository.findAll()).willReturn(mockList);

        List<CategoryByNameDto> listOfCategoryByNameDto = underTest.findAllByName();

        verify(categoryRepository).findAll();
        assertThat(listOfCategoryByNameDto).isInstanceOf(List.class);
        assertThat(listOfCategoryByNameDto.size()).isEqualTo(2);
    }

    @Test
    void emptyListFindAllByName(){

        mockList = Lists.emptyList();
        given(categoryRepository.findAll()).willReturn(mockList);

        assertThatThrownBy(()->underTest.findAllByName())
                .isInstanceOf(NotFoundException.class);
        verify(categoryRepository).findAll();
    }
}
