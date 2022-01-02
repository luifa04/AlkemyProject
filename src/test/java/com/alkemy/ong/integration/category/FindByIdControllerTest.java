package com.alkemy.ong.integration.category;

import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FindByIdControllerTest extends BaseCategoryTest{

    private final Long ID2GET = generateCategory().getId();
    private final String PATH = "/category/" + ID2GET;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());
        CategoryRequestUpdate categoryRequestUpdate = generateCategoryRequestUpdate();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.GET, new HttpEntity<>(categoryRequestUpdate,headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ReturnNullIfIdNotExist() {
        when(categoryRepository.findById(eq(ID2GET))).thenReturn(Optional.empty());

        login(RoleEnum.ADMIN.getRoleName());

        CategoryRequestUpdate categoryRequestUpdate = generateCategoryRequestUpdate();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.GET, new HttpEntity<>(categoryRequestUpdate, headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void CategoryByIdSuccess() {
        when(categoryRepository.findById(eq(ID2GET)))
                .thenReturn(Optional.of(generateCategory()));

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                        new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }


}
