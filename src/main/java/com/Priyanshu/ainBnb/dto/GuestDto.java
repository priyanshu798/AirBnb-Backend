package com.Priyanshu.ainBnb.dto;

import com.Priyanshu.ainBnb.entity.Booking;
import com.Priyanshu.ainBnb.entity.User;
import com.Priyanshu.ainBnb.entity.enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
public class GuestDto {

    private Long id;

    private String name;

    private Gender gender;

    private Integer age;
}
