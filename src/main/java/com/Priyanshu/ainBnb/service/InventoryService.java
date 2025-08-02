package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.*;
import com.Priyanshu.ainBnb.entity.Room;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InventoryService  {
    void initializeRoomForAYear(Room room);

    void deletaAllInventories(Room room);


    Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest);

    List<InventoryDto> getAllInventoriesInRoom(Long roomId);

    void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto);
}
