package com.Priyanshu.ainBnb.dto;

import com.Priyanshu.ainBnb.entity.HotelContactInfo;
import com.Priyanshu.ainBnb.entity.Room;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class HotelDto {

    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo contactInfo;

    private boolean active;


}
