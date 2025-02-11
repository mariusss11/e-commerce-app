package com.marius.ecommerce.payment;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated // this annotation makes that all the attributes that I have in the class are validated
public record Customer(

        String id,
        @NotNull(message = "Firstname is required")
        String firstName,
        @NotNull(message = "Lastname is required")
        String lastName,
        @NotNull(message = "Email is required")
        @Email(message = "The customer is not correctly formatted")
        String email
) {
}
