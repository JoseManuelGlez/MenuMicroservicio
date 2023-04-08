package com.example.menumicroservicio.web.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateMenuResponse {
    private String name;
    private String price;
    private String category;
    private String status;
    private byte[] image;
}
