package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.entity.Room;

public interface InventoryService  {
    void initializeRoomForAYear(Room room);

    void deleteFutureInventories(Room room);



}
