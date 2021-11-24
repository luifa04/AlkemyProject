package com.alkemy.ong.model;


import com.sun.istack.NotNull;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "activities")
@SQLDelete(sql = "UPDATE activities SET deleted = true WHERE id_post = ?")
@Where(clause = "deleted = false")
public class Activity {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String content;

    @NotNull
    private String image;

    private Date timestamp;

    private boolean deleted;

    public Activity(){

    }

}
