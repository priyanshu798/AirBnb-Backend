package com.Priyanshu.ainBnb.repository;

import com.Priyanshu.ainBnb.entity.Inventory;
import com.Priyanshu.ainBnb.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    void deleteByDateAfterAndRoom(LocalDate localDate, Room room);

    void deleteByRoom(Room room);
}
