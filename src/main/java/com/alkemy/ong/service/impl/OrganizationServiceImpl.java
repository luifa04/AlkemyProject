package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.*;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.dto.OrganizationRequest;
import com.alkemy.ong.dto.OrganizationResponse;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.OrganizationMapper;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import com.alkemy.ong.util.UpdateFields;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    private final OrganizationRepository organizationRepository;
    private final MessageSource messageSource;
    private final UpdateFields updateFields;
    @Autowired
    private OrganizationMapper organizationMapper;


    @Override
    public Organization findById(Long id){
        String notFoundOrganizationMessage = messageSource.getMessage("organization.notFound", null, Locale.US);
        return organizationRepository.findById(id).orElseThrow(()->new NotFoundException(notFoundOrganizationMessage));
    }


    @Override
    @Transactional
    public OrganizationResponse updatePublicData(OrganizationRequest organizationRequest) {

        Organization organization = getOrganization();

        updateFields.updateIfNotBlankAndNotEqual(organizationRequest.getName(), organization.getName(), organization::setName , "name");
        updateFields.updateIfNotBlankAndNotEqual(organizationRequest.getImage(), organization.getImage(), organization::setImage , "image");
        updateFields.updateIfNotBlankAndNotEqual(organizationRequest.getEmail(), organization.getEmail(), organization::setEmail , "email");
        updateFields.updateIfNotBlankAndNotEqual(organizationRequest.getWelcomeText(), organization.getWelcomeText(), organization::setWelcomeText , "welcome text");

        updateFields.updateIfNotEmptyAndNotEqual(organizationRequest.getPhone(), organization.getPhone(), organization::setPhone , "phone");
        updateFields.updateIfNotEmptyAndNotEqual(organizationRequest.getAddress(), organization.getAddress(), organization::setAddress , "address");
        updateFields.updateIfNotEmptyAndNotEqual(organizationRequest.getAboutUsText(), organization.getAboutUsText(), organization::setAboutUsText , "about us text");
        updateFields.updateIfNotEmptyAndNotEqual(organizationRequest.getFacebookUrl(), organization.getFacebookUrl(), organization::setFacebookUrl , "facebookUrl");
        updateFields.updateIfNotEmptyAndNotEqual(organizationRequest.getInstagramUrl(), organization.getInstagramUrl(), organization::setInstagramUrl , "instagramUrl");
        updateFields.updateIfNotEmptyAndNotEqual(organizationRequest.getLinkedinUrl(), organization.getLinkedinUrl(), organization::setLinkedinUrl , "linkedinUrl");

        if (updateFields.isHasUpdate()){
            organization.setDateUpdate(LocalDateTime.now());
        }

        return new OrganizationResponse(organization.getName()
                                        ,organization.getImage()
                                        ,organization.getAddress()
                                        ,organization.getPhone()
                                        ,organization.getEmail()
                                        ,organization.getWelcomeText()
                                        ,organization.getAboutUsText()
                                        ,organization.getFacebookUrl()
                                        ,organization.getInstagramUrl()
                                        ,organization.getLinkedinUrl());
    }

    public Organization getOrganization() {
        String notFoundOrganizationMessage = messageSource.getMessage("organization.notFound", null, Locale.US);
        Organization organization = organizationRepository.findAll().stream().findFirst()
                                                        .orElseThrow(()-> new NotFoundException(notFoundOrganizationMessage));
        return organization;
    }

    public List<OrganizationPublicDto> getAllOrganizations() {
        List<Organization> entities = organizationRepository.findAll();
        List<OrganizationPublicDto> result = organizationMapper.modelList2DTOList(entities);
        return result;
    }

}
