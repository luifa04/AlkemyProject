package com.alkemy.ong.integration.category;

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
public class DeleteCategoryControllerTest extends BaseCategoryTest {

    private final Long ID2DELETE = generateCategory().getId();
    private final String PATH_DELETE = PATH + "/" + ID2DELETE;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH_DELETE),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(categoryRepository.findById(ID2DELETE)).thenReturn(Optional.empty());

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH_DELETE),
                HttpMethod.DELETE, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void DeleteCategorySuccess() {
        when(categoryRepository.findById(eq(ID2DELETE)))
                .thenReturn(Optional.of(generateCategory()));

        login(RoleEnum.ADMIN.getRoleName());

        ResponseEntity<?> response =
                testRestTemplate.exchange(createURLWithPort(PATH_DELETE), HttpMethod.DELETE, new HttpEntity<>(headers), String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
