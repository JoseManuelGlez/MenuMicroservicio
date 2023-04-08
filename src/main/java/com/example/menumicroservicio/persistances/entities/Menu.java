package com.example.menumicroservicio.persistances.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "menus")
@Getter
@Setter
public class Menu {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (unique = true)
    private String name;
    private String price;
    private String category;
    private String status;
    private byte[] image;
}
