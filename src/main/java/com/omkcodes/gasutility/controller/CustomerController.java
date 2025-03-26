package com.omkcodes.gasutility.controller;

import com.omkcodes.gasutility.enums.CustomerStatus;
import com.omkcodes.gasutility.exception.InvalidCustomerIDException;
import com.omkcodes.gasutility.model.Customer;
import com.omkcodes.gasutility.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.createNewCustomer(
                    customer.getCustomerId(),
                    customer.getName(),
                    customer.getPhone(),
                    customer.getEmail(),
                    customer.getAddress(),
                    customer.getStatus()
            );
            return ResponseEntity.ok(createdCustomer);
        } catch (InvalidCustomerIDException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable String customerId) {
        try {
            Optional<Customer> customer = customerService.findCustomerById(customerId);
            return customer.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (InvalidCustomerIDException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getCustomersByStatus(CustomerStatus.ACTIVE);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<String> updateCustomer(
            @PathVariable String customerId,
            @RequestBody Customer customer) {
        boolean updated = customerService.updateCustomer(
                customerId, customer.getName(), customer.getPhone(),
                customer.getEmail(), customer.getAddress(), customer.getStatus()
        );

        if (updated) {
            return ResponseEntity.ok("Customer updated successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteCustomer(@PathVariable String customerId) {
        boolean deleted = customerService.deleteCustomer(customerId);
        if (deleted) {
            return ResponseEntity.ok("Customer deleted successfully!");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}