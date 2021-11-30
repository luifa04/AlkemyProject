package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("organization")

public class OrganizationController {

    @Autowired
    private IOrganizationService organizationService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationPublicDto>> getAll() {
        try {
            List<OrganizationPublicDto> organizations = organizationService.getAllOrganizations();
            return ResponseEntity.ok().body(organizations);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }

    }


}
