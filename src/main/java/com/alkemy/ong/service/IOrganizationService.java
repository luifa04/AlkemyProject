package com.alkemy.ong.service;

import com.alkemy.ong.dto.OrganizationPublicDto;

import java.util.List;

public interface IOrganizationService {

    List<OrganizationPublicDto> getAllOrganizations();
}
