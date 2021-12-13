package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.dto.SlidePublicDto;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.model.Slide;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganizationMapper {

    @Autowired
    private SlideMapper slideMapper;

    public OrganizationPublicDto model2DTO(Organization model){
        OrganizationPublicDto dto = new OrganizationPublicDto();
        dto.setName(model.getName());
        dto.setImage(model.getImage());
        dto.setAddress(model.getAddress());
        dto.setPhone(model.getPhone());
        dto.setFacebookUrl(model.getFacebookUrl());
        dto.setInstagramUrl(model.getInstagramUrl());
        dto.setLinkedinUrl(model.getLinkedinUrl());
        List<SlidePublicDto> slideDto = slideMapper.slideList2DTOList((List<Slide>)model.getSlides());
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
}
