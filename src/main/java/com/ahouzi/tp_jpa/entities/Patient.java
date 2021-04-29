package com.ahouzi.tp_jpa.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Entity
@Table(name = "PATIENTS")
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Patient implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOM",length = 25)
    private String nom;
    @Column(name = "DateNaissance")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    private int score;
    private boolean malade;


}
