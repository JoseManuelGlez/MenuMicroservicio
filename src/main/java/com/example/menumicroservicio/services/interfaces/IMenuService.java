package com.example.menumicroservicio.services.interfaces;

import com.example.menumicroservicio.web.requests.CreateChangeStatusRequest;
import com.example.menumicroservicio.web.requests.CreateMenuRequest;
import com.example.menumicroservicio.web.responses.BaseResponse;

public interface IMenuService {
    BaseResponse create(CreateMenuRequest request);

    BaseResponse changeStatus(CreateChangeStatusRequest request);

    void delete(Long id);
}
