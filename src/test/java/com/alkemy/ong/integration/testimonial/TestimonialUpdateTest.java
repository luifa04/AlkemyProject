package com.alkemy.ong.integration.testimonial;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.dto.TestimonialResponse;
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

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestimonialUpdateTest extends BaseTestimonialTest{
    private final Long ID2UPDATE = generateTestimonial().getId();
    private final String PATH = "/testimonials/" + ID2UPDATE;

    @Test
    public void ReturnUnauthorizedIfUserIsNotADMIN() {
        login(RoleEnum.USER.getRoleName());
        TestimonialRequest testimonialRequest = generateTestimonialRequest();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(testimonialRequest,headers), Object.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void ReturnBadRequestIfAnyAttributeIsNull() {
        login(RoleEnum.ADMIN.getRoleName());

        TestimonialRequest testimonialRequest = generateTestimonialRequest();
        testimonialRequest.setName("");
        testimonialRequest.setContent("");

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(testimonialRequest, headers), Object.class);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void ReturnNotFoundIfIdNotExist() {
        when(testimonialRepository.findById(eq(ID2UPDATE))).thenReturn(Optional.empty());

        login(RoleEnum.ADMIN.getRoleName());

        TestimonialRequest testimonialRequest = generateTestimonialRequest();

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH),
                HttpMethod.PUT, new HttpEntity<>(testimonialRequest, headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void UpdateTestimonialSuccess() {
        Testimonial testimonialModified = generateTestimonial();
        testimonialModified.setId(1L);
        testimonialModified.setName("Modified");
        testimonialModified.setContent("Modified");
        testimonialModified.setImage("modified.jpg");

        when(testimonialRepository.findById(ID2UPDATE)).thenReturn(Optional.of(generateTestimonial()));

        when(testimonialRepository.save(isA(Testimonial.class))).thenReturn(testimonialModified);

        login(RoleEnum.ADMIN.getRoleName());

        TestimonialRequest testimonialRequest = generateTestimonialRequest();

        testimonialRequest.setName("Modified");
        testimonialRequest.setContent("Modified");

        ResponseEntity<TestimonialResponse> response = testRestTemplate.exchange(
                createURLWithPort(PATH), HttpMethod.PUT, new HttpEntity<>(testimonialRequest, headers), TestimonialResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());

    }
}
