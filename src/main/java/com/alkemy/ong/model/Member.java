package com.alkemy.ong.model;


import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="members")
@SQLDelete(sql = "UPDATE members SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "cannot be null")
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    @NotNull(message = "cannot be null")
    private String image;
    private String description;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime dateCreation;
    @LastModifiedDate
    private LocalDateTime dateUpdate;
    private boolean deleted = Boolean.FALSE;

}


