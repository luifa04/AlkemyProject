package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.ITestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TestimonialServiceImpl implements ITestimonialService {

    private final TestimonialRepository testimonialRepository;

    @Override
    public Testimonial addTestimonial(TestimonialRequest testimonialRequest) {

        Testimonial testimonial = new Testimonial();
        testimonial.setName(testimonialRequest.getName());
        testimonial.setContent(testimonialRequest.getContent());

        if (testimonialRequest.getImage() != null){
            testimonial.setImage(testimonialRequest.getImage());
        }

        return testimonialRepository.save(testimonial);

    }
}
