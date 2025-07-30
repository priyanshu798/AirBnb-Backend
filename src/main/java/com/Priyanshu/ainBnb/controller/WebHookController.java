package com.Priyanshu.ainBnb.controller;

import com.Priyanshu.ainBnb.service.BookingService;
import com.stripe.exception.SignatureVerificationException;
import com.stripe.model.Event;
import com.stripe.net.Webhook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/webhook")
@RequiredArgsConstructor
@Slf4j
public class WebHookController {

    private final BookingService bookingService;

    @Value("${stripe.webhook.secret}")
    private String endPointSecret;

    @PostMapping("/payment")
    public ResponseEntity<Void> capturePayments(@RequestBody String payLoad, @RequestHeader("Stripe-Signature") String sigHeader) {
        try {
            log.info("capturing payments controller");

            Event event = Webhook.constructEvent(payLoad, sigHeader, endPointSecret);
            bookingService.capturePayment(event);
            return ResponseEntity.noContent().build();
        }
        catch (SignatureVerificationException e) {
            throw new RuntimeException();
        }
    }

}
