package com.eventstec.api.services.impl;

import com.eventstec.api.domain.coupon.Coupon;
import com.eventstec.api.domain.coupon.CouponMapper;
import com.eventstec.api.domain.coupon.CouponRequestDTO;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.repositories.CouponRepository;
import com.eventstec.api.repositories.EventRepository;
import com.eventstec.api.services.CouponService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CouponServiceImpl implements CouponService {
    private final CouponMapper couponMapper;
    private final CouponRepository couponRepository;
    private final EventRepository eventRepository;

    public CouponServiceImpl(final CouponMapper couponMapper,
                             final CouponRepository couponRepository,
                             final EventRepository eventRepository){
        this.couponMapper = couponMapper;
        this.couponRepository = couponRepository;
        this.eventRepository = eventRepository;
    }
    @Override
    public Coupon create(UUID event_id, CouponRequestDTO couponRequestDTO) {
        Event event = this.eventRepository.findById(event_id).orElseThrow(()->new RuntimeException("Error"));
        Coupon newEvent = this.couponMapper.couponRequestDTOForCouponEntity(couponRequestDTO, event);
        return this.couponRepository.save(newEvent);
    }
}
