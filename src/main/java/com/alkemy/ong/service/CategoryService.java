/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.service;

import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author mateo
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public Category findById(Long id){
        Optional<Category> rta= categoryRepository.findById(id);
        if (rta.isPresent()) {
            Category category= rta.get();
            return category;
        }else{
          throw  new Error("Error 404");
        }
    }
}
