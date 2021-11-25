package com.alkemy.ong.model;


import com.sun.istack.NotNull;
import com.sun.istack.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "organizations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE charactr SET deleted = true WHERE id=? and version=?")
@Where(clause = "deleted=false")
public class OrganizationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    private String name;

    @Nullable
    private String address;

    @Nullable
    private Long phone;

    @NotNull
    private String email;

    @NotNull
    private String welcomeText;

    @Nullable
    private String aboutUsText;

    @CreatedDate
    @NotNull
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreation;

    @NotNull
    private boolean deleted = Boolean.FALSE;
}


