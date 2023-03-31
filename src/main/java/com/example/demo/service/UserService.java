package com.example.demo.service;

import com.example.demo.controller.UserRequestDto;
import com.example.demo.controller.UserResponseDto;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void signUp(UserRequestDto userRequestDto) {
        UserEntity userEntity = new UserEntity(userRequestDto.getAccountId(), userRequestDto.getPassword());

        userRepository.save(userEntity);
    }

    public UserResponseDto getUser(Long id){
        //UserEntity userEntity = userRepository.findById(id).orElseThrow(() -> new RuntimeException());

        //return new UserResponseDto(userEntity.getAccountId(), userEntity.getPassword());
        UserResponseDto userResponseDto = userRepository.findById(id)
                .map(user -> new UserResponseDto(
                        user.getAccountId(), user.getPassword()
                ))
                .orElseThrow(() -> new RuntimeException());

        return userResponseDto;
    }

}
