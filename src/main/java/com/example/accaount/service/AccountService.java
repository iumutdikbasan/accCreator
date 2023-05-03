package com.example.accaount.service;


import com.example.accaount.dto.AccaountDto;
import com.example.accaount.dto.AccaountDtoConverter;
import com.example.accaount.dto.CreateAccountRequest;
import com.example.accaount.dto.CustomerAccountDtoConverter;
import com.example.accaount.model.Account;
import com.example.accaount.model.Customer;
import com.example.accaount.model.Transaction;
import com.example.accaount.repository.AccaountRepository;
import com.example.accaount.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class AccountService {

    private final AccaountRepository accaountRepository;
    private final CustomerRepository customerService;

    private final AccaountDtoConverter converter;

    public AccountService(AccaountRepository accaountRepository,
                          CustomerService customerService,

                          CustomerAccountDtoConverter converter) {
        this.accaountRepository = accaountRepository;
        this.customerService = customerService;

        this.converter = converter;
    }

        public AccaountDto createAccount(CreateAccountRequest createAccountRequest){
        Customer customer = customerService.findCustomerById(createAccountRequest.getCustomerId());

            Account account = new Account(
                    customer,
                    createAccountRequest.getInitialCredit(),
                    LocalDateTime.now());

            if (createAccountRequest.getInitialCredit().compareTo(BigDecimal.ZERO) > 0) {
                Transaction transaction = new Transaction(createAccountRequest.getInitialCredit(),account);
                        account.getTransaction().add(transaction);
            }
            return converter.convert(accaountRepository.save(account));
        }
}
