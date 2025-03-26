package com.omkcodes.gasutility.service;

import com.omkcodes.gasutility.enums.CustomerStatus;
import com.omkcodes.gasutility.exception.InvalidCustomerIDException;
import com.omkcodes.gasutility.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void displayCustomerDetails(String customerId);

    Optional<Customer> findCustomerById(String customerId) throws InvalidCustomerIDException;

    List<Customer> getCustomersByStatus(CustomerStatus status);

    List<String> getAllCustomerNames();

    void showAllCustomers();

    Customer createNewCustomer(String customerId, String name, String phone, String email, String address,
                               CustomerStatus status) throws InvalidCustomerIDException;

    boolean updateCustomer(String customerId, String name, String phone, String email, String address,
                           CustomerStatus status);

    boolean deleteCustomer(String customerId);
}