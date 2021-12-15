package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.ActivityRequest;
import com.alkemy.ong.dto.ActivityResponse;
import com.alkemy.ong.dto.TestimonialRequest;
import com.alkemy.ong.dto.TestimonialResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Activity;
import com.alkemy.ong.model.Testimonial;
import com.alkemy.ong.repository.ActivityRepository;
import com.alkemy.ong.repository.TestimonialRepository;
import com.alkemy.ong.service.ITestimonialService;
import com.alkemy.ong.util.UpdateFields;

import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Locale;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class TestimonialServiceImpl implements ITestimonialService {

	private final TestimonialRepository testimonialRepository;
	private final MessageSource messageSource;
	private final UpdateFields updateFields;

	@Override
	public ResponseEntity<?> delete(Long id) {
		String notFoundTestimonialMessage = messageSource.getMessage("testimonial.notFound", null, Locale.US);
		String deletedTestimonialMessage = messageSource.getMessage("testimonial.isDeleted", null, Locale.US);

		Testimonial testimonial = testimonialRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(notFoundTestimonialMessage));
		testimonialRepository.delete(testimonial);

		return new ResponseEntity<>(deletedTestimonialMessage, HttpStatus.OK);
	}

	@Override
	public Testimonial addTestimonial(TestimonialRequest testimonialRequest) {

		Testimonial testimonial = new Testimonial();
		testimonial.setName(testimonialRequest.getName());
		testimonial.setContent(testimonialRequest.getContent());

		if (testimonialRequest.getImage() != null) {
			testimonial.setImage(testimonialRequest.getImage());
		}

		return testimonialRepository.save(testimonial);
	}

	@Override
	@Transactional
	public TestimonialResponse updateTestimonial(TestimonialRequest testimonialRequest, Long id) {

		String notFoundTestimonialMessage = messageSource.getMessage("testimonial.notFound", null, Locale.US);

		Testimonial testimonial = testimonialRepository.findById(id)
				.orElseThrow(() -> new NotFoundException(notFoundTestimonialMessage));

		updateFields.updateIfNotBlankAndNotEqual(testimonialRequest.getName(), testimonial.getName(), testimonial::setName,
				"name");
		updateFields.updateIfNotBlankAndNotEqual(testimonialRequest.getContent(), testimonial.getContent(),
				testimonial::setContent, "content");
		updateFields.updateIfNotBlankAndNotEqual(testimonialRequest.getImage(), testimonial.getImage(), testimonial::setImage,
				"image");

		if (updateFields.isHasUpdate()) {
			testimonial.setDateUpdate(LocalDateTime.now());
		}

		return new TestimonialResponse(testimonial.getName(), testimonial.getContent(), testimonial.getImage(), 
				testimonial.getDateCreation().toString(), testimonial.getDateUpdate().toString());

	}

}