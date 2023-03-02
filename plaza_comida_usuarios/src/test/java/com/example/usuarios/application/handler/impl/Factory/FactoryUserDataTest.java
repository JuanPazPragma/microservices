package com.example.usuarios.application.handler.impl.Factory;

import com.example.usuarios.application.dto.request.RegisterRequestDto;
import com.example.usuarios.application.dto.request.UserRequestDto;
import com.example.usuarios.application.dto.response.ResponseClientDto;
import com.example.usuarios.application.dto.response.ResponseDto;
import com.example.usuarios.application.dto.response.RolResponseDto;
import com.example.usuarios.application.dto.response.UserResponseDto;
import com.example.usuarios.domain.model.RolModel;
import com.example.usuarios.domain.model.UserModel;
import com.example.usuarios.infrastructure.out.jpa.entity.RolEntity;
import com.example.usuarios.infrastructure.out.jpa.entity.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FactoryUserDataTest {

    public static UserRequestDto getUserRequestDto(Long rolId) {
        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName("Juan");
        userRequestDto.setLastName("Paz");
        userRequestDto.setIdNumber("100");
        userRequestDto.setPhone("54450");
        userRequestDto.setEmail("juan@gmail.com");
        userRequestDto.setPassword("1234");
        userRequestDto.setRolId(rolId);

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

    public static UserModel getUserModel(Long rolId, String rolName) {
        UserModel userModel = new UserModel();

        userModel.setId(1L);
        userModel.setName("Juan");
        userModel.setLastName("Paz");
        userModel.setIdNumber("100");
        userModel.setPhone("54450");
        userModel.setEmail("juan@gmail.com");
        userModel.setPassword("1234");
        userModel.setRolId(getRolModel(rolId, rolName));

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

    public static UserEntity getUserEntity2() {
        UserEntity userEntity = new UserEntity();

        userEntity.setId(1L);
        userEntity.setName("Juan");
        userEntity.setLastName("Paz");
        userEntity.setIdNumber("100");
        userEntity.setPhone("54450");
        userEntity.setEmail("juan2@gmail.com");
        userEntity.setPassword("1234");
        userEntity.setRolId(getRolEntity());

        return userEntity;
    }

    public static RolModel getRolModel(Long rolId, String roleName) {
        RolModel rolModel = new RolModel();

        rolModel.setId(rolId);
        rolModel.setName(roleName);
        rolModel.setDescription("descripcion");

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

    public static RegisterRequestDto getRegisterRequestDto() {
        RegisterRequestDto registerRequestDto = new RegisterRequestDto();

        registerRequestDto.setName("Juan");
        registerRequestDto.setLastName("Paz");
        registerRequestDto.setIdNumber("100");
        registerRequestDto.setPhone("54450");
        registerRequestDto.setEmail("juan@gmail.com");
        registerRequestDto.setPassword("1234");

        return registerRequestDto;
    }

    public static ResponseDto getResponseClientDto() {
        ResponseDto responseClientDto = new ResponseDto();

        responseClientDto.setMessage("");
        responseClientDto.setError(false);
        responseClientDto.setData(getUserRequestDto());

        return responseClientDto;
    }

    public static ResponseEntity<ResponseDto> getResponseEntity() {
        ResponseDto responseClientDto = getResponseClientDto();
        return new ResponseEntity<>(responseClientDto, HttpStatus.FOUND);
    }

    public static UserResponseDto getUserRequestDto() {
        UserResponseDto userRequestDto = new UserResponseDto();

        userRequestDto.setName("Juan");
        userRequestDto.setLastName("Paz");
        userRequestDto.setIdNumber("123");
        userRequestDto.setPhone("123");
        userRequestDto.setEmail("juan@gmail.com");

        return userRequestDto;
    }


}
