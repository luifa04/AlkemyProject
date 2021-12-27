package com.alkemy.ong.integration.testimonial;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.security.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.isA;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestimonialCreateTest  extends BaseTestimonialTest{
    private final String PATH = "/testimonials";

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());
        TestimonialRequest testimonialRequest = generateTestimonialRequest();
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(testimonialRequest, headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ReturnBadRequestIfAnyAttributeIsNull() {
        login(RoleEnum.ADMIN.getRoleName());

        TestimonialRequest testimonialRequest = generateTestimonialRequest();
        testimonialRequest.setName("");
        testimonialRequest.setContent("");

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.POST, new HttpEntity<>(testimonialRequest, headers), Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void CreateATestimonialSuccessful() {
        when(testimonialRepository.save(isA(Testimonial.class))).thenReturn(generateTestimonial());

        login(RoleEnum.ADMIN.getRoleName());

        TestimonialRequest testimonialRequest = generateTestimonialRequest();

        ResponseEntity<Object> response = testRestTemplate.exchange(
                createURLWithPort(PATH), HttpMethod.POST, new HttpEntity<>(testimonialRequest, headers), Object.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());

    }

}
