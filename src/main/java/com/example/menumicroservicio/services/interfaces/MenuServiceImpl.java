package com.example.menumicroservicio.services.interfaces;

import com.example.menumicroservicio.persistances.entities.Menu;
import com.example.menumicroservicio.persistances.repositories.IMenuRepository;
import com.example.menumicroservicio.web.requests.CreateChangeStatusRequest;
import com.example.menumicroservicio.web.requests.CreateFindByIdRequest;
import com.example.menumicroservicio.web.requests.CreateMenuRequest;
import com.example.menumicroservicio.web.responses.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements IMenuService{
    @Autowired
    private IMenuRepository repository;


    @Override
    public BaseResponse create(CreateMenuRequest request) {
        Menu menu = repository.save(from(request));

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(menu)
                .message("The menu was saved")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.CREATED)
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @Override
    public BaseResponse changeStatus(CreateChangeStatusRequest request) {
        Menu menu = findAndEnsureExist(request.getMenuId());

        menu.setStatus(request.getStatus());
        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(menu)
                .message("The status was changed")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @Override
    public BaseResponse delete(CreateFindByIdRequest request) {
        repository.delete(findAndEnsureExist(request.getMenuId()));

        return BaseResponse.builder()
                .sessionId(request.getSessionId())
                .data(null)
                .message("The product was eliminated")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    private Menu from(CreateMenuRequest request){
        Menu menu = new Menu();

        menu.setCategory(request.getCategory());
        menu.setImage(request.getImage());
        menu.setName(request.getName());
        menu.setPrice(request.getPrice());
        menu.setStatus(request.getStatus());
        return menu;
    }

    private Menu findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

}
