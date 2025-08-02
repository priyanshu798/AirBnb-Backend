package com.Priyanshu.ainBnb.controller;

import com.Priyanshu.ainBnb.dto.InventoryDto;
import com.Priyanshu.ainBnb.dto.UpdateInventoryRequestDto;
import com.Priyanshu.ainBnb.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<List<InventoryDto>> getAllInventoriesInRoom(@PathVariable("roomId") Long roomId) {
        return ResponseEntity.ok(inventoryService.getAllInventoriesInRoom(roomId));
    }

    @GetMapping("/rooms/{roomId}")
    public ResponseEntity<Void> updateInventory(@PathVariable("roomId") Long roomId, @RequestBody UpdateInventoryRequestDto updateInventoryRequestDto) {
       inventoryService.updateInventory(roomId, updateInventoryRequestDto);
       return ResponseEntity.noContent().build();
    }
}
