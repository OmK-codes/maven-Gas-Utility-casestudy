package com.omkcodes.gasutility.repository;

import com.omkcodes.gasutility.enums.CustomerStatus;
import com.omkcodes.gasutility.model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomerRepository {
    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Customer> customerRowMapper = (rs, rowNum) -> new Customer(
            rs.getString("customer_id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("phone"),
            rs.getString("address"),
            CustomerStatus.valueOf(rs.getString("status"))
    );

    public boolean saveCustomer(Customer customer) {
        String query = "INSERT INTO customer (customer_id, name, email, phone, address, status) VALUES (?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(query, customer.getCustomerId().trim(), customer.getName().trim(),
                customer.getEmail().trim(), customer.getPhone().trim(), customer.getAddress().trim(),
                customer.getStatus().name()) > 0;
    }

    public Optional<Customer> findCustomerById(String customerId) {
        String query = "SELECT * FROM customer WHERE customer_id = ?";
        List<Customer> customers = jdbcTemplate.query(query, customerRowMapper, customerId.trim());
        return customers.isEmpty() ? Optional.empty() : Optional.of(customers.get(0));
    }

    public List<Customer> getAllCustomers() {
        String query = "SELECT * FROM customer";
        return jdbcTemplate.query(query, customerRowMapper);
    }

    public boolean updateCustomer(Customer customer) {
        String query = "UPDATE customer SET name = ?, email = ?, phone = ?, address = ?, status = ? WHERE customer_id = ?";
        return jdbcTemplate.update(query, customer.getName().trim(), customer.getEmail().trim(),
                customer.getPhone().trim(), customer.getAddress().trim(), customer.getStatus().name(),
                customer.getCustomerId().trim()) > 0;
    }

    public boolean deleteCustomer(String customerId) {
        String query = "DELETE FROM customer WHERE customer_id = ?";
        return jdbcTemplate.update(query, customerId.trim()) > 0;
    }
}