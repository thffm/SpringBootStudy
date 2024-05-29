package com.Estudos.test.resources;

import com.Estudos.test.dto.UserDto;
import com.Estudos.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
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

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto user, @PathVariable Long id){
        user = service.update(user,id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<UserDto> insert(@RequestBody UserDto dto){
        dto = service.insert(dto);

        URI uri = ServletUriComponentsBuilder.
                fromCurrentRequest().path("/{id}").
                buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

}
