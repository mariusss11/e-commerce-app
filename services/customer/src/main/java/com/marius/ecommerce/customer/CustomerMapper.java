package com.marius.ecommerce.customer;

import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

    public Customer toCustomer(@Valid CustomerRequest request) {
        if (request == null)
            return null;
        return Customer.builder()
                .id(request.id())
                .lastName(request.lastName())
                .fistName(request.fistName())
                .email(request.email())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getId(),
                customer.getFistName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}
