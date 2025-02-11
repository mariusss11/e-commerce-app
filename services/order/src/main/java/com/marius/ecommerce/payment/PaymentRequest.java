package com.marius.ecommerce.payment;

import com.marius.ecommerce.customer.CustomerResponse;
import com.marius.ecommerce.oder.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
}
