package com.Priyanshu.ainBnb.service;

import com.Priyanshu.ainBnb.entity.Booking;

public interface CheckoutService {

    String getCheckoutSession(Booking booking, String successUrl, String failureUrl);
}
