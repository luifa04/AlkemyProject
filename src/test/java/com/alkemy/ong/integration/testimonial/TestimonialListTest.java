package com.alkemy.ong.integration.testimonial;

import com.alkemy.ong.model.Testimonial;
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

import static org.mockito.ArgumentMatchers.eq;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TestimonialListTest extends BaseTestimonialTest{
    private final Long ID2GET =generateTestimonial().getId();
    private final String ONLY_PATH = "/testimonials/" + ID2GET;
    private final String PATH = "/testimonials?page=";
    private static final int SIZE_DEFAULT = 10;

    private List<Testimonial> testimonials = new ArrayList<>();

    @Test
    public void ReturnForbiddenIfUserIsNotLogged() {
        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH), HttpMethod.GET,
                new HttpEntity<>(headers), Object.class);
        assertEquals( HttpStatus.FORBIDDEN, response.getStatusCode());
    }


    @Test
    public void ReturnNotFoundIfPageOutOfRange() {
        int page = 15;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        testimonials = generateListTestimonial(SIZE_DEFAULT);
        Page<Testimonial> pagedTestimonials = new PageImpl<>(testimonials);

        when(testimonialRepository.findAll(eq(pageable))).thenReturn(pagedTestimonials);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response = testRestTemplate.exchange(createURLWithPort(PATH + page),
                HttpMethod.GET, new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

    }

    @Test
    public void ListTestimonialsByIdSuccess() {
        when(testimonialRepository.findById(eq(ID2GET)))
                .thenReturn(Optional.of(generateTestimonial()));

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(ONLY_PATH), HttpMethod.GET,
                        new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void ListTestimonialsSuccess() {
        int page = 1;
        Pageable pageable = PageRequest.of(page, SIZE_DEFAULT);
        testimonials = generateListTestimonial(SIZE_DEFAULT * 2);
        Page<Testimonial> pagedTestimonials =
                new PageImpl<>(testimonials, pageable, SIZE_DEFAULT * 2);

        when(testimonialRepository.findAll(eq(pageable))).thenReturn(pagedTestimonials);

        login(RoleEnum.USER.getRoleName());

        ResponseEntity<Object> response =
                testRestTemplate.exchange(createURLWithPort(PATH + page), HttpMethod.GET,
                        new HttpEntity<>(headers), Object.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
