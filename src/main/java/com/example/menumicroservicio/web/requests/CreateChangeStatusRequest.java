package com.example.menumicroservicio.web.requests;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateChangeStatusRequest {
    private Long menuId;
    private String status;
    private String sessionId;
}
