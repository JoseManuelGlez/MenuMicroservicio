package com.example.menumicroservicio.web.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateFindByIdRequest {
    private Long menuId;
    private String sessionId;
}
