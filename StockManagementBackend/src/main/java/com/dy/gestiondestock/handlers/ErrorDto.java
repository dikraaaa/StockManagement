package com.dy.gestiondestock.handlers;

import com.dy.gestiondestock.exception.ErrorCodes;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDto {

  private Integer httpCode;

  private ErrorCodes code;

  private String message;

  private List<String> errors = new ArrayList<>();

}
