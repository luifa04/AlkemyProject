package com.alkemy.ong.controller;

import com.alkemy.ong.dto.SlideRequest;
import com.alkemy.ong.dto.SlideResponse;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.ISlidesService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/slides")
@AllArgsConstructor
public class SlidesContoller {

    private final ISlidesService slidesService;

    @PostMapping
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<Slide> addSlide(@Valid @RequestBody SlideRequest slide) throws Exception {
            return new ResponseEntity<>(slidesService.addSlide(slide), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> deleteSlide(@Valid @PathVariable("id") Long id ) {
        return slidesService.delete(id);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<SlideResponse> slideDetail(@Valid @PathVariable Long id) {
            return new ResponseEntity<>(slidesService.detail(id), HttpStatus.OK);
    }
    
    @GetMapping()
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<?> findAllSlide(){
            return slidesService.findAll();
    }


    @PutMapping(path = "{id}")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<SlideResponse> updateNewsById(@PathVariable("id") Long id, @Valid @RequestBody SlideResponse slide){
        return new ResponseEntity<>(slidesService.updateSlidesById(id, slide), HttpStatus.OK);

    }

}
