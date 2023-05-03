package com.example.accaount.service;

import com.example.accaount.dto.CustomerDto;
import com.example.accaount.dto.CustomerDtoConverter;
import com.example.accaount.exception.CustomerNotFoundException;
import com.example.accaount.model.Customer;
import com.example.accaount.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class CustomerServiceTest {
    private CustomerService service;
    private  CustomerRepository customerRepository;
    private  CustomerDtoConverter converter;

    @BeforeEach
    public void setUp(){
        customerRepository = mock(CustomerRepository.class);
        converter = mock(CustomerDtoConverter.class);
        service = new CustomerService(customerRepository, converter);
    }
    @Test
        public void testFindByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
            Customer customer = new Customer("id", "name", "surname", Set.of());
            Mockito.when(CustomerRepository.findById("id").thenReturn(Optional.of(customer)));

            Customer result = service.findCustomerById("id");
            assertEquals(result, customer);
        }
    @Test
    public void testFindByCustomerId_whenCustomerIdDoesNotExists_shouldTrowCustomerNotFoundException(){
            Mockito.when(CustomerRepository.findById("id").thenReturn(Optional.empty()));

            assertThrows(CustomerNotFoundException.class,()-> service.findCustomerById("id"));
    }
    @Test
    public void testgetByCustomerId_whenCustomerIdExists_shouldReturnCustomer() {
        Customer customer = new Customer("id", "name", "surname", Set.of());
        Mockito.when(CustomerRepository.findById("id").thenReturn(Optional.of(customer)));
        Mockito.when(converter.converToCustomerDto(customer)).thenReturn(CustomerDto);

        Customer result = service.findCustomerById("id");
        assertEquals(result, CustomerDto);
    }
}
