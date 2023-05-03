package com.example.accaount.service;

import com.example.accaount.repository.AccaountRepository;

import java.time.Clock;
import java.util.UUID;
import java.util.function.Supplier;

public class AccountServiceTest {
    private final AccaountRepository accaountRepository;
    private final CustomerService customerService;
    private final Clock clock;
    private final Supplier<UUID> uuidSupplier;

    public AccountServiceTest(AccaountRepository accaountRepository,
                              CustomerService customerService,
                              Clock clock,
                              Supplier<UUID> uuidSupplier) {
        this.accaountRepository = accaountRepository;
        this.customerService = customerService;
        this.clock = clock;
        this.uuidSupplier = uuidSupplier;
    }

}