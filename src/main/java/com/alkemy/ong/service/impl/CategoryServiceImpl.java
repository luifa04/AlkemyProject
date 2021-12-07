/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.dto.CategoryRequestUpdate;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import java.time.LocalDateTime;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author mateo
 */
@Service
@AllArgsConstructor
public class CategoryServiceImpl implements ICategoryService{
    
    @Autowired
    public CategoryRepository categoryRepository;
    
    
    @Override
    public CategoryDto findById(Long id){
        Optional<Category> rta= categoryRepository.findById(id);
        if (rta.isPresent()) {
            Category category= rta.get();
            return mapEntityToDto(category);
        }
        return null;
    }
    
    @Override
	public CategoryDto createCategory(@Valid CategoryRequestUpdate category) {
		Category categoryEntity = new Category();
                mapDtoToEntity(categoryEntity, category);
                Category categoryCreate = categoryRepository.save(categoryEntity);
	        return mapEntityToDto(categoryCreate);
	}
        
        private CategoryDto mapEntityToDto(Category categoryUpdated) {
		CategoryDto categoryDto = new CategoryDto();
		categoryDto.setId(categoryUpdated.getId());
		categoryDto.setName(categoryUpdated.getName());
		categoryDto.setDescripcion(categoryUpdated.getDescription());
		categoryDto.setImage(categoryUpdated.getImage());
		categoryDto.setDateCreacion(categoryUpdated.getDateCreation().toString());
		categoryDto.setDateUpdate(categoryUpdated.getDateUpdate().toString());
		return categoryDto;
	}
        
        private void mapDtoToEntity(Category categoryEntity, @Valid CategoryRequestUpdate category) {
		categoryEntity.setName(category.getName());
		categoryEntity.setDescription(category.getDescripcion());
		categoryEntity.setImage(category.getImage());
		categoryEntity.setDateUpdate(LocalDateTime.now());
	}
}
