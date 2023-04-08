package com.example.menumicroservicio.web.rabbit;

import com.example.menumicroservicio.web.dtos.responses.BaseResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface IRabbitMQ {
    void sendToMenuCreateResponseQueue(BaseResponse response) throws JsonProcessingException;

    void sendToMenuCreateErrorQueue(BaseResponse response) throws JsonProcessingException;

    void sendToMenuChangeStatusResponseQueue(BaseResponse response) throws JsonProcessingException;

    void sendToMenuChangeStatusErrorQueue(BaseResponse response) throws JsonProcessingException;

    void sendToMenuViewResponseQueue(BaseResponse response) throws JsonProcessingException;
}
