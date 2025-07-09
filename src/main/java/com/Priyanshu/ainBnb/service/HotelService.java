package com.Priyanshu.ainBnb.service;


import com.Priyanshu.ainBnb.dto.HotelDto;


public interface HotelService {

    HotelDto createNewHotel(HotelDto hotelDto);

    HotelDto getHotelById(Long id);

    HotelDto updateHotelById(Long id, HotelDto hotelDto);

    void deleteHotelById(Long id);

    void acitvateHotel(Long id);


}
