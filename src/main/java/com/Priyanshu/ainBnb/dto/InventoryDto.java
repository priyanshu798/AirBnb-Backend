package com.Priyanshu.ainBnb.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto {


    private Long id;

    private LocalDate date;

    private Integer bookedCount;

    private Integer reservedCount;

    private Integer totalCount;

    private BigDecimal surgeFactor;

    private BigDecimal price; //base price * surge factor

    private String city;

    private Boolean closed;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
