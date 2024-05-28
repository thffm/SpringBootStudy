package com.Estudos.test.resources;

import com.Estudos.test.dto.UserDto;
import com.Estudos.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping(value = "/api/users")
public class UserResource implements Serializable {
    public static final long serialVersionUID = 1;

    @Autowired
    private UserService service;

    @RequestMapping
    public ResponseEntity<List<UserDto>> findAll(){
        List<UserDto> list = service.findAll();
        return ResponseEntity.ok().body(list);

    }

    @RequestMapping(value = "/{id}")
    public ResponseEntity<UserDto> findById(@PathVariable Long id){
        UserDto entity = service.findById(id);
        return ResponseEntity.ok().body(entity);
    }

}
