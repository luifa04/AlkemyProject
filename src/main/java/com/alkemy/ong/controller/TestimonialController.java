package com.alkemy.ong.controller;

import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.service.ITestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final ITestimonialService testimonialService;

    @PostMapping
    public ResponseEntity<Testimonial> addTestimonial(@Valid @RequestBody TestimonialRequest testimonialRequest){
        return new ResponseEntity<>(testimonialService.addTestimonial(testimonialRequest), HttpStatus.CREATED);
    }
}
