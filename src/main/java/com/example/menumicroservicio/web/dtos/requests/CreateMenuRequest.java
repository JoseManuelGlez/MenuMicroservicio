package com.example.menumicroservicio.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateMenuRequest {
    private String name;
    private String price;
    private String category;
    private String status;
    private String image;
    private String sessionId;
}
