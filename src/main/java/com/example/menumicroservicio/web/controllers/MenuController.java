package com.example.menumicroservicio.web.controllers;

import com.example.menumicroservicio.services.interfaces.IMenuService;
import com.example.menumicroservicio.utilities.MapperUtil;
import com.example.menumicroservicio.web.controllers.advices.MenuExceptions;
import com.example.menumicroservicio.web.dtos.requests.ViewRequest;
import com.example.menumicroservicio.web.rabbit.IRabbitMQ;
import com.example.menumicroservicio.web.dtos.requests.CreateChangeStatusRequest;
import com.example.menumicroservicio.web.dtos.requests.CreateFindByIdRequest;
import com.example.menumicroservicio.web.dtos.requests.CreateMenuRequest;
import com.example.menumicroservicio.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MenuController {
    @Autowired
    private IMenuService service;

    @Autowired
    private IRabbitMQ rabbitMQ;

    @Autowired
    private MenuExceptions exceptions;

    @RabbitListener(queues = "queue.menus_view")
    public void view(String payload) throws JsonProcessingException {
        ViewRequest request = MapperUtil.deserialize(payload, ViewRequest.class);
        BaseResponse response = service.View(request);
        rabbitMQ.sendToMenuViewResponseQueue(response);
    }

    @RabbitListener(queues = "queue.menus_create")
    public void create(String payload) throws JsonProcessingException {
        CreateMenuRequest request = MapperUtil.deserialize(payload, CreateMenuRequest.class);
        try {
            BaseResponse response = service.create(request);
            rabbitMQ.sendToMenuCreateResponseQueue(response);
            ViewRequest vr = new ViewRequest();
            vr.setSessionId(null);
            view(MapperUtil.serialize(vr));
        } catch (Exception e){
            exceptions.createError(e, request.getSessionId());
        }
    }

    @RabbitListener(queues = "queue.menus_change_status")
    public void changeStatus(String payload) throws JsonProcessingException {
        CreateChangeStatusRequest request = MapperUtil.deserialize(payload, CreateChangeStatusRequest.class);
        try {
            BaseResponse response = service.changeStatus(request);
            rabbitMQ.sendToMenuChangeStatusResponseQueue(response);
            ViewRequest vr = new ViewRequest();
            vr.setSessionId(null);
            view(MapperUtil.serialize(vr));
        }catch (Exception e){
            exceptions.changeStatusError(e, request.getSessionId());
        }
    }

    @RabbitListener(queues = "queue.menus_delete")
    public void delete(String payload) throws JsonProcessingException {
        CreateFindByIdRequest request = MapperUtil.deserialize(payload, CreateFindByIdRequest.class);
        service.delete(request);
        ViewRequest vr = new ViewRequest();
        vr.setSessionId(null);
        view(MapperUtil.serialize(vr));
    }
}
