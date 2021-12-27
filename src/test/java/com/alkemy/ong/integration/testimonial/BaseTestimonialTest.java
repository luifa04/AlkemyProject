package com.alkemy.ong.integration.testimonial;

import com.alkemy.ong.common.BaseGeneralTest;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

public class BaseTestimonialTest extends BaseGeneralTest {
    @MockBean
    protected TestimonialRepository testimonialRepository;

    protected Testimonial generateTestimonial(){
        Testimonial testimonial = new Testimonial();
        testimonial.setId(1L);
        testimonial.setName("testimonial");
        testimonial.setImage("testimonial.jpg");
        testimonial.setContent("content of testimonial");
        testimonial.setEnabled(Boolean.TRUE);

        return testimonial;
    }

    protected TestimonialRequest generateTestimonialRequest(){
        TestimonialRequest testimonialRequest = new TestimonialRequest();
        testimonialRequest.setName("testimonial");
        testimonialRequest.setContent("testimonial");
        return  testimonialRequest;
    }

    protected List<Testimonial> generateListTestimonial(int count) {
        List<Testimonial> testimonials = new ArrayList<>(count);
        for (int i = 1; i <= count; i++) {
            testimonials.add(generateTestimonial());
        }

        return testimonials;
    }
}
