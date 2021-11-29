package com.alkemy.ong.controller;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("organization")

public class OrganizationController {

    @Autowired
    private IOrganizationService IOrganizationService;

    @GetMapping("/public")
    public ResponseEntity<List<OrganizationPublicDto>> getAll() {
        List<OrganizationPublicDto> organizations = this.IOrganizationService.getAllOrganizations();
        return ResponseEntity.ok().body(organizations);

    }


}
