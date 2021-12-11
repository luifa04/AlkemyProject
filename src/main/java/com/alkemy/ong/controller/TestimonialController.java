package com.alkemy.ong.controller;

import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ITestimonialService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/testimonials")
@AllArgsConstructor
public class TestimonialController {

    private final ITestimonialService testimonialService;

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> deleteTestimonial(@Valid @PathVariable("id") Long id){
        return testimonialService.delete(id);
    }
}
