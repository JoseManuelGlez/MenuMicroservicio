package com.example.menumicroservicio.services.interfaces;

import com.example.menumicroservicio.web.dtos.requests.CreateChangeStatusRequest;
import com.example.menumicroservicio.web.dtos.requests.CreateFindByIdRequest;
import com.example.menumicroservicio.web.dtos.requests.CreateMenuRequest;
import com.example.menumicroservicio.web.dtos.requests.ViewRequest;
import com.example.menumicroservicio.web.dtos.responses.BaseResponse;

public interface IMenuService {
    BaseResponse create(CreateMenuRequest request);

    BaseResponse changeStatus(CreateChangeStatusRequest request);

    BaseResponse delete(CreateFindByIdRequest request);

    BaseResponse View(ViewRequest request);
}
