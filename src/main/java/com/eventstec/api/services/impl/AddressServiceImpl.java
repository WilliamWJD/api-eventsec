package com.eventstec.api.services.impl;

import com.eventstec.api.domain.address.Address;
import com.eventstec.api.domain.address.AddressMapper;
import com.eventstec.api.domain.event.Event;
import com.eventstec.api.domain.event.EventRequestDTO;
import com.eventstec.api.repositories.AddressRepository;
import com.eventstec.api.services.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    public AddressServiceImpl(final AddressRepository addressRepository, final AddressMapper addressMapper){
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }
    @Override
    public void createAddress(EventRequestDTO data, Event event) {
        this.addressRepository.save(this.addressMapper.mapperAddress(data, event));
    }
}
