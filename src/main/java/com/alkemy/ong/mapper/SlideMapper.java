package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.SlidePublicDto;
import com.alkemy.ong.model.Slide;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component

public class SlideMapper {

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
