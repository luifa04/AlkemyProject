package com.alkemy.ong.integration.category;

import com.alkemy.ong.common.BaseGeneralTest;
import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.repository.TestimonialRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class BaseCategoryTest extends BaseGeneralTest {

    final String PATH = "/category";

    @MockBean
    protected CategoryRepository categoryRepository;

    protected Category generateCategory(){
        Category category = new Category();
        category.setId(1L);
        category.setName("CategoryExample");
        category.setDescription("Example of description");
        category.setImage("http://image.jpg");
        category.setDateCreation(LocalDateTime.now());
        category.setDateUpdate(LocalDateTime.now());
        return category;
    }

    protected CategoryDto generateCategoryDto(){

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(1L);
        categoryDto.setName("CategoryExample");
        categoryDto.setDescription("Example of description");
        categoryDto.setImage("http://image.jpg");
        categoryDto.setDateCreation(LocalDateTime.now().toString());
        categoryDto.setDateUpdate(LocalDateTime.now().toString());

        return categoryDto;
    }

    protected List<Category> generateListCategory(int count) {
        List<Category> categories = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            categories.add(generateCategory());
        }

        return categories;
    }

    protected CategoryRequestUpdate generateCategoryRequestUpdate(){
        CategoryRequestUpdate categoryRequestUpdate = new CategoryRequestUpdate();
        categoryRequestUpdate.setName("Example");
        categoryRequestUpdate.setDescription("Example of description");
        categoryRequestUpdate.setImage("http://image.jpg");
        return categoryRequestUpdate;
    }

}
