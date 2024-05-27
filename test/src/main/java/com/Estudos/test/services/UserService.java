package com.Estudos.test.services;

import com.Estudos.test.dto.UserDto;
import com.Estudos.test.entity.User;
import com.Estudos.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService  implements Serializable {
    public static final long serialVersionUID = 1L;
    @Autowired
    private UserRepository repository;

    public List<UserDto> findAll(){
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
    }


}
