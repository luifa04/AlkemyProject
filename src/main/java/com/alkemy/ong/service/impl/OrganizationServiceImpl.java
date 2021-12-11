package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.*;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import lombok.RequiredArgsConstructor;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


import javax.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private final OrganizationRepository organizationRepository;
    private final MessageSource messageSource;
    private Boolean hasUpdate = Boolean.FALSE;

    @Override
    public Organization findById(Long id){
        String notFoundOrganizationMessage = messageSource.getMessage("organization.notFound", null, Locale.US);
        return organizationRepository.findById(id).orElseThrow(()->new NotFoundException(notFoundOrganizationMessage));
    }


    @Override
    @Transactional
    public OrganizationResponse updatePublicData(OrganizationRequest organizationRequest) {

        Organization organization = getOrganization();

        updateIfNotBlankAndNotEqual(organizationRequest.getName(), organization.getName(), organization::setName , "name");
        updateIfNotBlankAndNotEqual(organizationRequest.getImage(), organization.getImage(), organization::setImage , "image");
        updateIfNotBlankAndNotEqual(organizationRequest.getEmail(), organization.getEmail(), organization::setEmail , "email");
        updateIfNotBlankAndNotEqual(organizationRequest.getWelcomeText(), organization.getWelcomeText(), organization::setWelcomeText , "welcome text");

        updateIfNotEmptyAndNotEqual(organizationRequest.getPhone(), organization.getPhone(), organization::setPhone , "phone");
        updateIfNotEmptyAndNotEqual(organizationRequest.getAddress(), organization.getAddress(), organization::setAddress , "address");
        updateIfNotEmptyAndNotEqual(organizationRequest.getAboutUsText(), organization.getAboutUsText(), organization::setAboutUsText , "about us text");

        if (hasUpdate){
            organization.setDateUpdate(LocalDateTime.now());
        }

        return new OrganizationResponse(organization.getName()
                                        ,organization.getImage()
                                        ,organization.getAddress()
                                        ,organization.getPhone()
                                        ,organization.getEmail()
                                        ,organization.getWelcomeText()
                                        , organization.getAboutUsText());
    }

    public Organization getOrganization() {
        String notFoundOrganizationMessage = messageSource.getMessage("organization.notFound", null, Locale.US);
        Organization organization = organizationRepository.findAll().stream().findFirst()
                                                        .orElseThrow(()-> new NotFoundException(notFoundOrganizationMessage));
        return organization;
    }

    private <T> void updateIfNotBlankAndNotEqual(T source , T destination, Consumer<T> update, String parameterName){
        String notBeBlankMessage = messageSource.getMessage("organization.blank", null, Locale.US);
        if (source != null && !source.equals(destination)){
            if (source.getClass().equals(String.class) && ((String) source).isBlank()){
                throw new IllegalArgumentException(String.format("%s %s", parameterName, notBeBlankMessage));
            }
            update.accept(source);
            hasUpdate = Boolean.TRUE;
        }
    }

    private <T> void updateIfNotEmptyAndNotEqual(JsonNullable<T> source , T destination, Consumer<T> update, String parameterName){
        String notBeBlankMessage = messageSource.getMessage("organization.blank", null, Locale.US);
        if (source != null) {
            T internalSource = source.orElse(null);
            if (internalSource != null && internalSource.getClass().equals(String.class) && ((String) internalSource).isBlank()) {
                throw new IllegalArgumentException(String.format("%s %s", parameterName, notBeBlankMessage));
            }
            if(!Objects.equals(internalSource, destination)){
                update.accept(internalSource);
                hasUpdate = Boolean.TRUE;
            }
        }
    }

    public OrganizationPublicDto model2DTO(Organization model){
        OrganizationPublicDto dto = new OrganizationPublicDto();
        dto.setName(model.getName());
        dto.setImage(model.getImage());
        dto.setAddress(model.getAddress());
        dto.setPhone(model.getPhone());
        List<SlidePublicDto> slideDto = slideList2DTOList((List<Slide>)model.getSlides());
        dto.setSlidePublicDto(slideDto);

        return dto;
    }

    public List<OrganizationPublicDto> modelList2DTOList(List<Organization> entities){
        List<OrganizationPublicDto> dtos = new ArrayList<>();
        for (Organization entity : entities) {
            dtos.add(model2DTO(entity));
        }
        return dtos;
    }

    public List<OrganizationPublicDto> getAllOrganizations() {
        List<Organization> entities = organizationRepository.findAll();
        List<OrganizationPublicDto> result = this.modelList2DTOList(entities);
        return result;
    }

    public SlidePublicDto slideModel2DTO(Slide entity) {
        SlidePublicDto dto = new SlidePublicDto();
        dto.setId(entity.getId());
        dto.setImageUrl(entity.getImageUrl());
        dto.setText(entity.getText());
        dto.setOrderSlide(entity.getOrderSlide());
        dto.setOrganizationId(entity.getOrganizationId());

        return dto;
    }

    public List<SlidePublicDto> slideList2DTOList(List<Slide> entities) {
        List<SlidePublicDto> dtos = new ArrayList<>();
        for (Slide entity : entities) {
            dtos.add(this.slideModel2DTO(entity));
        }
        return dtos;
    }
}
