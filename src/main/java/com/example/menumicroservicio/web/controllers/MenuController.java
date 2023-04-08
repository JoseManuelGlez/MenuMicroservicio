package com.example.menumicroservicio.web.controllers;

import com.example.menumicroservicio.services.interfaces.IMenuService;
import com.example.menumicroservicio.utilities.MapperUtil;
import com.example.menumicroservicio.web.rabbit.IRabbitMQ;
import com.example.menumicroservicio.web.requests.CreateChangeStatusRequest;
import com.example.menumicroservicio.web.requests.CreateFindByIdRequest;
import com.example.menumicroservicio.web.requests.CreateMenuRequest;
import com.example.menumicroservicio.web.responses.BaseResponse;
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

    @RabbitListener(queues = "queue.menus_create")
    public void create(String payload) throws JsonProcessingException {
        CreateMenuRequest request = MapperUtil.deserialize(payload, CreateMenuRequest.class);
        BaseResponse response = service.create(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.menus_change_status")
    public void changeStatus(String payload) throws JsonProcessingException {
        CreateChangeStatusRequest request = MapperUtil.deserialize(payload, CreateChangeStatusRequest.class);
        BaseResponse response = service.changeStatus(request);
        rabbitMQ//metodo
    }

    @RabbitListener(queues = "queue.menus_delete")
    public void delete(String payload) throws JsonProcessingException {
        CreateFindByIdRequest request = MapperUtil.deserialize(payload, CreateFindByIdRequest.class);
        BaseResponse response = service.delete(request);
        rabbitMQ//metodo
    }
}
