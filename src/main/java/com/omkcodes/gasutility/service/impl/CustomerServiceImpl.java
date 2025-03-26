package com.omkcodes.gasutility.service.impl;

import com.omkcodes.gasutility.enums.CustomerStatus;
import com.omkcodes.gasutility.exception.InvalidCustomerIDException;
import com.omkcodes.gasutility.model.Customer;
import com.omkcodes.gasutility.repository.CustomerRepository;
import com.omkcodes.gasutility.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void displayCustomerDetails(String customerId) {
        findCustomerById(customerId).ifPresentOrElse(
                customer -> System.out.println("Customer Details: " + customer),
                () -> System.out.println("Customer not found with ID: " + customerId)
        );
    }

    @Override
    public Optional<Customer> findCustomerById(String customerId) throws InvalidCustomerIDException {
        validateCustomerId(customerId);
        return customerRepository.findCustomerById(customerId);
    }

    @Override
    public List<Customer> getCustomersByStatus(CustomerStatus status) {
        return customerRepository.getAllCustomers().stream()
                .filter(customer -> customer.getStatus() == status)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getAllCustomerNames() {
        return customerRepository.getAllCustomers().stream()
                .map(Customer::getName)
                .toList();
    }

    @Override
    public void showAllCustomers() {
        List<Customer> customers = customerRepository.getAllCustomers();
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
        } else {
            customers.forEach(System.out::println);
        }
    }

    @Override
    public Customer createNewCustomer(String customerId, String name, String phone, String email, String address,
                                      CustomerStatus status) throws InvalidCustomerIDException {
        validateCustomerId(customerId);
        validateCustomerDetails(name, phone, email, address);

        Customer newCustomer = new Customer(customerId, name, email, phone, address, status);
        boolean isSaved = customerRepository.saveCustomer(newCustomer);

        if (!isSaved) {
            throw new RuntimeException("Failed to create new customer.");
        }

        return newCustomer;
    }

    @Override
    public boolean updateCustomer(String customerId, String name, String phone, String email, String address,
                                  CustomerStatus status) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(customerId);
        if (existingCustomer.isEmpty()) {
            System.out.println("Customer not found for update: " + customerId);
            return false;
        }

        validateCustomerDetails(name, phone, email, address);
        Customer updatedCustomer = new Customer(customerId, name, email, phone, address, status);
        return customerRepository.updateCustomer(updatedCustomer);
    }

    @Override
    public boolean deleteCustomer(String customerId) {
        Optional<Customer> existingCustomer = customerRepository.findCustomerById(customerId);
        if (existingCustomer.isEmpty()) {
            System.out.println("Customer not found for deletion: " + customerId);
            return false;
        }

        return customerRepository.deleteCustomer(customerId);
    }

    private void validateCustomerId(String customerId) throws InvalidCustomerIDException {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new InvalidCustomerIDException("Customer ID cannot be null or empty.");
        }
    }

    private void validateCustomerDetails(String name, String phone, String email, String address) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name cannot be empty.");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone number cannot be empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be empty.");
        }
        if (address == null || address.trim().isEmpty()) {
            throw new IllegalArgumentException("Address cannot be empty.");
        }
    }
}