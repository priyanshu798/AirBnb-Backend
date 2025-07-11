package com.Priyanshu.ainBnb.dto;
import com.Priyanshu.ainBnb.entity.Hotel;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class RoomDto {


    private Long id;
    private Hotel hotel;
    private String type;
    private BigDecimal basePrice;
    private String[] photos;
    private String[] amenities;
    private Integer totalCount;
    private Integer capacity;
}
