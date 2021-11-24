package com.alkemy.ong.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE news enabled WHERE id = ?")
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    private String image;

    @CreatedDate
    @NotNull
    @Column(updatable = false)
    private LocalDateTime dateCreation;

    @LastModifiedDate
    private LocalDateTime dateUpdate;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pk_categoryId")
    private Category categoryId;

    public News(String name, String content, String image, Category categoryId){
        this.name = name;
        this.content = content;
        this.image = image;
        this.categoryId = categoryId;
    }

}
