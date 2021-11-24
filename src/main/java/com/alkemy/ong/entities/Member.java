package com.alkemy.ong.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="members")
@SQLDelete(sql = "UPDATE posts SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class Member {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String facebookUrl;
    private String instagramUrl;
    private String linkedinUrl;
    private String image;
    private String description;
    private LocalDateTime timestamp;
    private boolean deleted = Boolean.FALSE;

}


