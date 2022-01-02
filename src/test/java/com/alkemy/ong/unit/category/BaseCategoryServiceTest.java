package com.alkemy.ong.unit.category;

import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.impl.CategoryServiceImpl;
import org.mockito.Mock;
import org.springframework.context.MessageSource;

import java.time.LocalDateTime;

public class BaseCategoryServiceTest {
    protected final long ID_CATEGORY = 1L;
    protected final long ID_CATEGORY_NOTFOUND = 2L;
    @Mock
    protected CategoryRepository categoryRepository;
    protected CategoryMapper categoryMapper;
    @Mock
    protected MessageSource messageSource;
    protected CategoryServiceImpl underTest;

    protected CategoryRequestUpdate mockCategoryUpdate;
    protected Category mockCategory;

    protected void generateMockCategoryUpdate(){

        mockCategoryUpdate = new CategoryRequestUpdate();
        mockCategoryUpdate.setName("Example");
        mockCategoryUpdate.setDescription("Example of description");
        mockCategoryUpdate.setImage("http://image.com/image.jpg");
    }

    protected void generateMockCategory(){

        mockCategory = new Category();
        mockCategory.setName("Ejemplo");
        mockCategory.setDescription("ejemplo de descripcion");
        mockCategory.setDateCreation(LocalDateTime.now());
        mockCategory.setDateUpdate(LocalDateTime.now());

    }


}
