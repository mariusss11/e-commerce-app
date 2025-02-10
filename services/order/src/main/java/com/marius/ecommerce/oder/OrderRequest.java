package com.marius.ecommerce.oder;

import com.marius.ecommerce.product.PurchaseRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.util.List;


import java.math.BigDecimal;

public record OrderRequest (
         Integer id,
         String reference,
         @Positive(message = "Order amount should be positive")
         BigDecimal amount,
         @NotNull(message = "Payment method should be precised ")
         PaymentMethod paymentMethod,

         @NotNull(message = "Customer should be present")
         @NotEmpty(message = "Customer should be present")
         @NotBlank(message = "Customer should be present")
         String customerId,
         @NotEmpty(message = "You should at least purchase one product")
         List<PurchaseRequest> products
) {
}
