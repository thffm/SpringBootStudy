package com.Estudos.test.services;

import com.Estudos.test.dto.UserDto;
import com.Estudos.test.entity.User;
import com.Estudos.test.repository.UserRepository;
import com.Estudos.test.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService  implements Serializable {
    public static final long serialVersionUID = 1L;
    @Autowired
    private UserRepository repository;

    @Transactional(readOnly = true)
    public List<UserDto> findAll(){
        List<User> list = repository.findAll();
        return list.stream().map(x -> new UserDto(x)).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UserDto findById(Long id) {
        Optional<User> obj = repository.findById(id);
        User entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
        return new UserDto(entity);
    }

    @Transactional
    public UserDto update(UserDto user, Long id){
        try {
            User entity = repository.getReferenceById(id);
            entity.setName(user.getName());
            entity.setAge(user.getAge());
            entity.setEmail(user.getEmail());
            entity = repository.save(entity);
            return  new UserDto(entity);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Id not found: Id{"+id+"}");
        }
    }

    @Transactional
    public UserDto insert(UserDto dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        user.setName(dto.getName());
        user = repository.save(user);
        return new UserDto(user);

    }
}
