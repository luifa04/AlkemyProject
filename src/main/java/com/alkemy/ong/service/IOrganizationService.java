package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.model.Organization;


import java.util.List;

public interface IOrganizationService {

    List<OrganizationPublicDto> getAllOrganizations();
    OrganizationResponse updatePublicData(OrganizationRequest organization);
    Organization findById(Long id);
    Organization getOrganization();
}
