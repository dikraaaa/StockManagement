package com.dy.gestiondestock.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationRequestDto {

    private String login;

    private String password;

}
