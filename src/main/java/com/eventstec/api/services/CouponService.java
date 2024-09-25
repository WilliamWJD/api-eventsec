package com.eventstec.api.services;

import com.eventstec.api.domain.coupon.Coupon;
import com.eventstec.api.domain.coupon.CouponRequestDTO;

import java.util.UUID;

public interface CouponService {
    /**
     * Create coupon.
     *
     * @param event_id         the event id
     * @param couponRequestDTO the coupon request dto
     * @return the coupon
     */
    Coupon create(UUID event_id, final CouponRequestDTO couponRequestDTO);
}
