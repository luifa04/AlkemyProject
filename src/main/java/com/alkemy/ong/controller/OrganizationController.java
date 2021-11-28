package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.service.IOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    private final IOrganizationService iOrganizationService;

    @PatchMapping("/public")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<OrganizationResponse> updatePublicData(@Valid @RequestBody OrganizationRequest organization){
        return new ResponseEntity<OrganizationResponse>(iOrganizationService.updatePublicData(organization), HttpStatus.OK);
    }
}
