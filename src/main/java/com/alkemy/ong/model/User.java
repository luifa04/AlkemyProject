package com.alkemy.ong.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE user SET enabled = false WHERE userId = ?")
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
@EntityListeners(AuditingEntityListener.class)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    @Length(min = 5, message = "Ingrese una contraseña de minímo 5 carácteres")
    private String password;

    @Nullable
    private String photo;

    private Boolean enabled=true;

    @CreatedDate
    private LocalDateTime dateCreation;

    @LastModifiedDate
    private LocalDateTime dateUpdate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="roleId")
    private Role role;

    @Transient
    private String token;

}
