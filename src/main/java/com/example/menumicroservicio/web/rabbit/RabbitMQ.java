package com.example.menumicroservicio.web.rabbit;

import com.example.menumicroservicio.utilities.MapperUtil;
import com.example.menumicroservicio.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQ implements IRabbitMQ{

    @Autowired
    private RabbitTemplate template;

    @Override
    public void sendToMenuCreateResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.menus_create_response", serialize);
    }

    @Override
    public void sendToMenuCreateErrorQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.menus_create_errors", serialize);
    }

    @Override
    public void sendToMenuChangeStatusResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.menus_change_status_response", serialize);
    }

    @Override
    public void sendToMenuChangeStatusErrorQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.menus_change_status_errors", serialize);
    }

    @Override
    public void sendToMenuViewResponseQueue(BaseResponse response) throws JsonProcessingException {
        String serialize = MapperUtil.serialize(response);
        template.convertAndSend("queue.menus_view_response", serialize);
    }
}
