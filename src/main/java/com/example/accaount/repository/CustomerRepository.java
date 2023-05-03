package com.example.accaount.repository;

import com.example.accaount.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    Customer findCustomerById(String customerId);
}
