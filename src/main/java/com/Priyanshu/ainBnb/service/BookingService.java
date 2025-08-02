package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.dto.BookingDto;
import com.Priyanshu.ainBnb.dto.BookingRequest;
import com.Priyanshu.ainBnb.dto.GuestDto;
import com.Priyanshu.ainBnb.dto.HotelReportDto;
import com.stripe.model.Event;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface BookingService {
    BookingDto initialiseBooking(BookingRequest bookingRequest);

    BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);

    String initiatePayments(Long bookingId);

    void capturePayment(Event event);

    void cancelBooking(Long bookingId);

    String getBookingStatus(Long bookingId);

    List<BookingDto> getAllBookingsByHotelId(Long hotelId);

    HotelReportDto getHotelReport(Long hotelId, LocalDate startDate, LocalDate endDate);
}
