package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.HotelDto;
import com.Priyanshu.ainBnb.dto.HotelPriceDto;
import com.Priyanshu.ainBnb.dto.HotelSearchRequest;
import com.Priyanshu.ainBnb.entity.Room;
import org.springframework.data.domain.Page;

public interface InventoryService  {
    void initializeRoomForAYear(Room room);

    void deletaAllInventories(Room room);


    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);
}
