package com.Priyanshu.ainBnb.dto;

import com.Priyanshu.ainBnb.entity.Booking;
import com.Priyanshu.ainBnb.entity.User;
import com.Priyanshu.ainBnb.entity.enums.Gender;
import jakarta.persistence.*;

import java.util.Set;

public class GuestDto {

    private Long id;

    private User user;

    private String name;

    private Gender gender;

    private Integer age;

    private Set<Booking> bookings;
}
