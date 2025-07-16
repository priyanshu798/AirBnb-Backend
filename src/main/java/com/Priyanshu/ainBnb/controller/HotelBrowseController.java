package com.Priyanshu.ainBnb.controller;

import com.Priyanshu.ainBnb.dto.HotelDto;
import com.Priyanshu.ainBnb.dto.HotelInfoDto;
import com.Priyanshu.ainBnb.dto.HotelSearchRequest;
import com.Priyanshu.ainBnb.service.HotelService;
import com.Priyanshu.ainBnb.service.InventoryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelDto>> searchHotels(@RequestBody HotelSearchRequest hotelSearchRequest) {
        Page<HotelDto> page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId) {

        return ResponseEntity.ok(hotelService.getHotelInfoById(hotelId));

    }
}
