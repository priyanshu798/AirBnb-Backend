package com.Priyanshu.ainBnb.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class LogInDto {
    private String email;
    private String password;
}
