package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.security.SecurityConstant;
import com.alkemy.ong.service.IOrganizationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/organization")
@AllArgsConstructor
public class OrganizationController {

    private final IOrganizationService organizationService;

    @GetMapping("/public")
    @PreAuthorize(SecurityConstant.USER)
    public ResponseEntity<List<OrganizationPublicDto>> getAll() {
        try {
            List<OrganizationPublicDto> organizations = organizationService.getAllOrganizations();
            return ResponseEntity.ok().body(organizations);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PatchMapping("/public")
    @PreAuthorize(SecurityConstant.ADMIN)
    public ResponseEntity<OrganizationResponse> updatePublicData(@Valid @RequestBody OrganizationRequest organization){
        return new ResponseEntity<OrganizationResponse>(organizationService.updatePublicData(organization), HttpStatus.OK);
    }

}
