package com.alkemy.ong.service.serviceImpl;

import com.alkemy.ong.dto.OrganizationPublicDto;
import com.alkemy.ong.model.Organization;
import com.alkemy.ong.repository.OrganizationRepository;
import com.alkemy.ong.service.IOrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IOrganizationServiceImpl implements IOrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    public OrganizationPublicDto model2DTO(Organization model){
        OrganizationPublicDto dto = new OrganizationPublicDto();
        dto.setName(model.getName());
        dto.setImage(model.getImage());
        dto.setAddress(model.getAddress());
        dto.setPhone(model.getPhone());

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
}
