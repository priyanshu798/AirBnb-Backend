package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.BookingDto;
import com.Priyanshu.ainBnb.dto.BookingRequest;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);
}
