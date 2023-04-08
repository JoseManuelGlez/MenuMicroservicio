package com.example.menumicroservicio.web.controllers.advices;

import com.example.menumicroservicio.web.dtos.responses.BaseResponse;
import com.example.menumicroservicio.web.rabbit.IRabbitMQ;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.persistence.Access;
import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class MenuExceptions {

    @Autowired
    private IRabbitMQ rabbitMQ;

    public void changeStatusError(Exception e, String sessionId) throws JsonProcessingException {
        BaseResponse response = BaseResponse.builder()
                .sessionId(sessionId)
                .message(e.getMessage())
                .data("No fue posible cambiar el estatus")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(412)
                .build();
        rabbitMQ.sendToMenuChangeStatusErrorQueue(response);
    }

    public void createError(Exception e, String sessionId) throws JsonProcessingException {
        BaseResponse response = BaseResponse.builder()
                .sessionId(sessionId)
                .message(e.getMessage())
                .data("No fue posible agregar al menu")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(412)
                .build();
        rabbitMQ.sendToMenuCreateErrorQueue(response);
        throw new AmqpRejectAndDontRequeueException("error");
    }
}
