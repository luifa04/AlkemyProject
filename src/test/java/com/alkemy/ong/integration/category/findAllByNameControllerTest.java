package com.alkemy.ong.integration.category;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class findAllByNameControllerTest extends BaseCategoryTest{

    private final String PATH = "/category?page=";

    private static final int SIZE_DEFAULT = 10;

    @Test
    public void ReturnNotFoundIfPageOutOfRange() {
        int page = 15;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        List<Category> categories = generateListCategory(SIZE_DEFAULT);
        Page<Category> pagedCategories = new PageImpl<>(categories);

        when(categoryRepository.findAll(eq(pageable))).thenReturn(pagedCategories);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH + page),
                HttpMethod.GET, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void ListCategoriesSuccess() {
        int page = 1;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        List<Category> categories = generateListCategory(SIZE_DEFAULT * 2);
        Page<Category> pagedCategories =
                new PageImpl<>(categories, pageable, SIZE_DEFAULT * 2);

        when(categoryRepository.findAll(eq(pageable))).thenReturn(pagedCategories);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(PATH + page), HttpMethod.GET,
                        new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
