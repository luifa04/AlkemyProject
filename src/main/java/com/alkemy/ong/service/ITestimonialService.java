package com.alkemy.ong.service;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;

public interface ITestimonialService {

    Testimonial addTestimonial(TestimonialRequest testimonialRequest);

}
