package com.example.accaount.service;


import com.example.accaount.dto.CustomerDto;
import com.example.accaount.dto.CustomerDtoConverter;
import com.example.accaount.exception.CustomerNotFoundException;
import com.example.accaount.model.Customer;
import com.example.accaount.repository.CustomerRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter converter;

    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter converter) {
        this.customerRepository = customerRepository;
        this.converter = converter;
    }

    protected Customer findCustomerById(String id){
        return customerRepository.findById(id)
                .orElseThrow(
                () -> new CustomerNotFoundException("Customer not find by id : " + id));
    }

    public CustomerDto getCustomerById(String customerId) {
        return converter.convertToCustomerDto(findCustomerById(customerId));
    }
}
