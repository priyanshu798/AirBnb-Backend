package com.Priyanshu.ainBnb.controller;

import com.Priyanshu.ainBnb.dto.RoomDto;
import com.Priyanshu.ainBnb.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/hotels/{hotelId}/rooms")
@RequiredArgsConstructor
public class RoomAdminController {

    private final RoomService roomService;

    @PostMapping
    public ResponseEntity<RoomDto> createNewRoom(@PathVariable("hotelId") Long hotelId, @RequestBody RoomDto roomDto) {
        RoomDto roomDto1 = roomService.createNewRoom(hotelId,roomDto);
        return new ResponseEntity<>(roomDto1, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RoomDto>> getAllRoomsInHotel(@PathVariable("hotelId") Long hotelId) {
        return ResponseEntity.ok(roomService.getAllRoomsInHotel(hotelId));
    }

    @GetMapping("/{roomId}")
    public ResponseEntity<RoomDto> getRoomById(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok(roomService.getRoomById(roomId));
    }

    @DeleteMapping("/{roomId}")
    public ResponseEntity<Void> deleteRoomById(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId) {
        roomService.deleteRoomById(roomId);
        return ResponseEntity.noContent().build();

    }
    @PutMapping
    public ResponseEntity<RoomDto> updateRoomById(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Long roomId,
                                                  @RequestBody RoomDto roomDto) {
        RoomDto roomDto1 = roomService.updateRoomById(hotelId, roomId, roomDto);
        return new ResponseEntity<>(roomDto1, HttpStatus.OK);
    }


}
