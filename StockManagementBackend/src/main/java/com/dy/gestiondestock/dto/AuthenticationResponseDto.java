package com.dy.gestiondestock.dto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthenticationResponseDto {

    private String accessToken;

}
