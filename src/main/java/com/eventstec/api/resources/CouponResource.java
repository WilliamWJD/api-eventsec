package com.eventstec.api.resources;

import com.eventstec.api.domain.coupon.Coupon;
import com.eventstec.api.domain.coupon.CouponRequestDTO;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.services.CouponService;
import com.eventstec.api.services.impl.CouponServiceImpl;
import com.eventstec.api.services.impl.EventServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/coupon")
public class CouponResource {
    private final CouponService couponService;
    public CouponResource(final CouponServiceImpl couponServiceImpl){
        this.couponService = couponServiceImpl;
    }
    @PostMapping("/event/{eventId}")
    public ResponseEntity<Coupon> create(@RequestBody final CouponRequestDTO couponRequestDTO, @PathVariable UUID eventId){
        Coupon coupon = this.couponService.create(eventId, couponRequestDTO);
        return ResponseEntity.ok(coupon);
    }
}
