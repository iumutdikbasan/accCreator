package com.example.accaount.dto;

import com.example.accaount.model.Customer;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    private final CustomerAccountDtoConverter converter;

    public CustomerDtoConverter(CustomerAccountDtoConverter converter) {
        this.converter = converter;
    }

    public AccaountCustomerDto convertToAccaountCustomer(Customer from){
        if(from == null){
            return new AccaountCustomerDto("","","");
        }else
        return new AccaountCustomerDto(from.getId(), from.getName(), from.getSurname());
    }
    public CustomerDto converToCustomerDto(Customer from){
        return new CustomerDto(
                from.getId(),
                from.getName(),
                from.getSurname(),
                from.getAccounts().stream().map(converter::convert).collect(Collectors.toSet())
        );
    }


}
