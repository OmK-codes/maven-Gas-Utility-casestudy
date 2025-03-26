package com.omkcodes.gasutility.service;

import java.sql.Connection;

public class TestConnectionService {
    public static void main(String[] args) {
        ConnectionService connectionService = new ConnectionService(); // Create an instance
        Connection connection = connectionService.getConnection(); // Call method on instance

        if (connection != null) {
            System.out.println("Database connection test successful!");
        } else {
            System.out.println("Database connection test failed!");
        }
    }
}
