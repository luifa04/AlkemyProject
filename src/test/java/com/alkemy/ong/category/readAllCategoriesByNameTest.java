package com.alkemy.ong.category;

import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;

@ExtendWith(MockitoExtension.class)
public class readAllCategoriesByNameTest {

    @Mock
    private CategoryRepository categoryRepository;
    private CategoryMapper categoryMapper;
    @Mock
    private MessageSource messageSource;
    private CategoryServiceImpl underTest;

    @BeforeEach
    void setUp(){
        categoryMapper = new CategoryMapper();
        underTest = new CategoryServiceImpl(categoryRepository, categoryMapper, messageSource);
    }


}
