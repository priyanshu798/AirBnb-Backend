package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.*;
import com.Priyanshu.ainBnb.entity.Hotel;
import com.Priyanshu.ainBnb.entity.Inventory;
import com.Priyanshu.ainBnb.entity.Room;
import com.Priyanshu.ainBnb.entity.User;
import com.Priyanshu.ainBnb.exception.ResourceNotFoundException;
import com.Priyanshu.ainBnb.repository.HotelMinPriceRepository;
import com.Priyanshu.ainBnb.repository.InventoryRepository;
import com.Priyanshu.ainBnb.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.Priyanshu.ainBnb.util.AppUtils.getCurrentUser;

@Service
@Slf4j
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;
    private final HotelMinPriceRepository hotelMinPriceRepository;
    private final RoomRepository roomRepository;

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
    public Page<HotelPriceDto> searchHotels(HotelSearchRequest hotelSearchRequest) {
        log.info("Searching hotel for {} city, from {} to {}", hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate());

        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());
        long dayCount = ChronoUnit.DAYS.between(hotelSearchRequest.getStartDate(), hotelSearchRequest.getEndDate()) + 1;

        //business logic - 90 days
        Page<HotelPriceDto> hotelPage =  hotelMinPriceRepository.findHotelsWithAvailableInventory(hotelSearchRequest.getCity(), hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate(), hotelSearchRequest.getRoomsCount(),
                dayCount, pageable);

        return hotelPage;
    }

    @Override
    public List<InventoryDto> getAllInventoriesInRoom(Long roomId) {
        log.info("Getting all the inventories of room by roomID "+ roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResourceNotFoundException("Room not found with id " + roomId));

        User user = getCurrentUser();
        if (!Objects.equals(room.getHotel().getOwner().getId(), user.getId())) {
            throw new AccessDeniedException("You do not the owner of this room with id " + roomId);
        }

        return inventoryRepository.findByRoomOrderByDate(room).stream()
                .map((element) -> modelMapper.map(element, InventoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void updateInventory(Long roomId, UpdateInventoryRequestDto updateInventoryRequestDto) {
        log.info("Updating the inventories of room by roomID "+ roomId);
        Room room = roomRepository.findById(roomId).orElseThrow(() ->
                new ResourceNotFoundException("Room not found with id " + roomId));

        User user = getCurrentUser();
        if (!Objects.equals(room.getHotel().getOwner().getId(), user.getId())) {
            throw new AccessDeniedException("You do not the owner of this room with id " + roomId);
        }
        inventoryRepository.getInventoryAndLockBeforeUpdate(roomId, updateInventoryRequestDto.getStartDate(),
                updateInventoryRequestDto.getEndDate());

        inventoryRepository.updateInventory(roomId, updateInventoryRequestDto.getStartDate(),
                updateInventoryRequestDto.getEndDate(), updateInventoryRequestDto.isClosed(),
                updateInventoryRequestDto.getSurgeFactor());


    }
}
