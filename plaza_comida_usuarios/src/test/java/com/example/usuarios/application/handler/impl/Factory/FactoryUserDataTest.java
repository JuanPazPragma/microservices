package com.example.usuarios.application.handler.impl.Factory;

import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.ResponseDto;
import com.example.usuarios.application.dto.response.RolResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;

public class FactoryUserDataTest {

    public static UserRequestDto getUserRequestDto() {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName("Juan");
        userRequestDto.setLastName("Paz");
        userRequestDto.setIdNumber("100");
        userRequestDto.setPhone("54450");
        userRequestDto.setEmail("juan@gmail.com");
        userRequestDto.setPassword("1234");
        userRequestDto.setRolId(1L);

        return userRequestDto;
    }

    public static UserResponseDto getUserResponseDto() {
        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setName("Juan");
        userResponseDto.setLastName("Paz");
        userResponseDto.setIdNumber("100");
        userResponseDto.setPhone("54450");
        userResponseDto.setEmail("juan@gmail.com");
        userResponseDto.setRolId(getRolResponseDto());

        return userResponseDto;
    }

    public static UserModel getUserModel() {
        UserModel userModel = new UserModel();

        userModel.setId(1L);
        userModel.setName("Juan");
        userModel.setLastName("Paz");
        userModel.setIdNumber("100");
        userModel.setPhone("54450");
        userModel.setEmail("juan@gmail.com");
        userModel.setPassword("1234");
        userModel.setRolId(getRolModel());

        return userModel;
    }

    public static UserEntity getUserEntity() {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setName("Juan");
        userEntity.setLastName("Paz");
        userEntity.setIdNumber("100");
        userEntity.setPhone("54450");
        userEntity.setEmail("juan@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRolId(getRolEntity());

        return userEntity;
    }

    public static RolModel getRolModel() {
        RolModel rolModel = new RolModel();

        rolModel.setId(1L);
        rolModel.setName("ROLE_ADMINISTRADOR");
        rolModel.setDescription("Administrador");

        return rolModel;
    }

    public static RolEntity getRolEntity() {
        RolEntity rolEntity = new RolEntity();

        rolEntity.setId(1L);
        rolEntity.setName("ROLE_ADMINISTRADOR");
        rolEntity.setDescription("Administrador");

        return rolEntity;
    }

    public static RolResponseDto getRolResponseDto() {
        RolResponseDto rolResponseDto = new RolResponseDto();

        rolResponseDto.setName("ROLE_ADMINISTRADOR");
        rolResponseDto.setDescription("Administrador");

        return rolResponseDto;
    }

    public static ResponseDto getResponseDto(Object data, boolean error, String message) {
        ResponseDto responseDto = new ResponseDto();

        responseDto.setError(error);
        responseDto.setData(data);
        responseDto.setMessage(message);

        return responseDto;
    }


}
