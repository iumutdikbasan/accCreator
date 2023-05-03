package com.example.accaount.dto;

import com.example.accaount.model.Account;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class AccaountDtoConverter {

    private final CustomerDtoConverter customerDtoConverter;
    private final TransactionDtoConverter transactionDtoConverter;

    public AccaountDtoConverter(CustomerDtoConverter customerDtoConverter,
                                TransactionDtoConverter transactionDtoConverter){
        this.customerDtoConverter = customerDtoConverter;
        this.transactionDtoConverter = transactionDtoConverter;
    }
    public AccaountDto convert(Account from){

        return new AccaountDto(from.getId(),
                from.getBalance(),
                from.getCreationDate(),
                customerDtoConverter.convertToAccaountCustomer(from.getCustomer()),
                Objects.requireNonNull(from.getTransaction())
                        .stream()
                        .map(TransactionDtoConverter::convert)
                        .collect(Collectors.toSet()));
    }

}
