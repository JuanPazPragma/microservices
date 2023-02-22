package com.example.plaza_comidas.application.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RestaurantRequestDto {

    @NotNull(message = "Nombre es obligatorio")
    @Pattern(regexp = "^(?!\\d+$).*", message = "El nombre no puede estar compuesto unicamente de numeros")
    private String name;
    @NotNull(message = "Direccion es obligatorio")
    private String address;
    @NotNull(message = "Identificador del propietario es obligatorio")
    private Long ownerId;
    @NotNull(message = "Numero de telefono es obligatorio")
    @Pattern(regexp = "[+]?\\d+(\\d+)?", message = "Ingrese un formato valido para el telefono")
    @Size(max = 13, message = "El telefono del restaurante debe tener un maximo de 13 caracteres")
    private String phoneNumber;
    @NotNull(message = "Url del logo es obligatorio")
    private String urlLogo;
    @NotNull(message = "NIT es obligatorio")
    @Pattern(regexp = "\\d+(\\d+)?", message = "NIT debe ser numerico")
    private String nit;
}
