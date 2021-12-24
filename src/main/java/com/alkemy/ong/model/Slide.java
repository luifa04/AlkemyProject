/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.alkemy.ong.model;




import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;


@Data
@NoArgsConstructor
@Entity
@Table(name="slides")
public class Slide {
    
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message="the Url of the image cannot be empty")
    private String imageUrl;
    
    @NotNull(message="El text cannot be empty")
    private String text;
    
    @NotNull(message="the order cannot be empty")
    private Integer orderSlide;
    
    @NotNull(message="the organizationId cannot be empty")
    private Long organizationId;

}
