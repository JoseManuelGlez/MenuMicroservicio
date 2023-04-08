package com.example.menumicroservicio.web.responses;

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
