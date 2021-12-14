package com.alkemy.ong.model;

import javax.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "categories")
@Data
@SQLDelete(sql = "UPDATE categories SET enabled = false WHERE id = ?")
@Where(clause = "enabled=true")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Name cannot be null")
    private String name;
    private String description;
    private String image;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateCreation;
    @LastModifiedDate
    private LocalDateTime dateUpdate;
    private boolean enabled = true;
}
