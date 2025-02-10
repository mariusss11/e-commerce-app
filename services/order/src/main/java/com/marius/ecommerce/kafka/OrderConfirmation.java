package com.marius.ecommerce.kafka;

import com.marius.ecommerce.customer.CustomerResponse;
import com.marius.ecommerce.oder.PaymentMethod;
import com.marius.ecommerce.product.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<PurchaseResponse> products
) {
}
