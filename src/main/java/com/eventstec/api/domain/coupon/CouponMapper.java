package com.eventstec.api.domain.coupon;

import com.eventstec.api.domain.event.Event;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class CouponMapper {
    public Coupon couponRequestDTOForCouponEntity(final CouponRequestDTO couponRequestDTO, final Event event){
        Coupon coupon = new Coupon();
        coupon.setCode(couponRequestDTO.code());
        coupon.setDiscount(couponRequestDTO.discount());
        coupon.setValid(new Date(couponRequestDTO.valid()));
        coupon.setEvent(event);
        return coupon;
    }
}
