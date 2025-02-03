package com.marius.ecommerce.customer;

import com.marius.ecommerce.exception.CustomerNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(@Valid CustomerRequest requst) {
        var customer = customerRepository.findById(requst.id())
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("Cannot update customer:: No customer found with the provided ID :: %s", requst.id())
                ));
        mergeCustomer(customer, requst);
        customerRepository.save(customer);
    }

    private void mergeCustomer(Customer customer, @Valid CustomerRequest requst) {
        if (StringUtils.isNotBlank(requst.fistName())) {
            customer.setFistName(requst.fistName());
        }

        if (StringUtils.isNotBlank(requst.lastName())) {
            customer.setLastName(requst.lastName());
        }

        if (StringUtils.isNotBlank(requst.email())) {
            customer.setEmail(requst.email());
        }

        if (requst.address() != null) {
            customer.setAddress(requst.address());
        }

    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public Boolean existsById(String customerId) {
        return customerRepository.findById(customerId)
                .isPresent();
    }

    public CustomerResponse findById(String customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        format("No customer found with the provided ID:: %s", customerId)
                ));
    }

    public void deleteCustomer(String customerId) {
        customerRepository.deleteById(customerId);
    }
}
