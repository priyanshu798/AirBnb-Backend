package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.HotelDto;
import com.Priyanshu.ainBnb.dto.HotelSearchRequest;
import com.Priyanshu.ainBnb.entity.Hotel;
import com.Priyanshu.ainBnb.entity.Inventory;
import com.Priyanshu.ainBnb.entity.Room;
import com.Priyanshu.ainBnb.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for (; !today.isAfter(endDate); today = today.plusDays(1)) {
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();
            inventoryRepository.save(inventory);
        }

    }

    @Override
    public void deletaAllInventories(Room room) {
        log.info("Deleting inventories for room id :  {}", room.getId());
        inventoryRepository.deleteByRoom(room);

    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotel for {} city, from {} to {}", hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());

        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        long dayCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;
        Page<Hotel> hotelPage =  inventoryRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate(), hotelSearchRequest.getRoomsCount(),
                dayCount, pageable);

        return hotelPage.map((element) -> modelMapper.map(element, HotelDto.class));
    }
}
