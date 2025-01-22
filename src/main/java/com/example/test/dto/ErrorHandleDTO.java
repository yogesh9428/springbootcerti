package com.example.test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.bind.ConstructorBinding;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorHandleDTO {
    private String error;
}
