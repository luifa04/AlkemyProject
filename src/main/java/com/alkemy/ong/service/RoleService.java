package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Service
@Transactional
@AllArgsConstructor
public class RoleService {

    private RoleRepository roleRepository;
    private MessageSource messageSource;

    public Role findByName(String name){
        String notFoundOrganizationMessage = messageSource.getMessage("role.notFound", null, Locale.US);
        return roleRepository.findByName(name).orElseThrow(()->new NotFoundException(String.format("role %s %s", name, notFoundOrganizationMessage)));
    }

}
