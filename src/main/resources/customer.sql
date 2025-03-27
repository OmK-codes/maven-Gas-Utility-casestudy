CREATE TABLE customer (
    customer_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phone VARCHAR(20) UNIQUE NOT NULL,
    address TEXT NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE', 'SUSPENDED') NOT NULL -- Matches CustomerStatus enum
);
INSERT INTO customer (customer_id, name, email, phone, address, status) VALUES
('CUST001', 'John Doe', 'john.doe@example.com', '9876543210', '123 Main St, City A', 'ACTIVE'),
('CUST002', 'Jane Smith', 'jane.smith@example.com', '8765432109', '456 Elm St, City B', 'ACTIVE'),
('CUST003', 'Alice Johnson', 'alice.johnson@example.com', '7654321098', '789 Pine St, City C', 'INACTIVE'),
('CUST004', 'Bob Williams', 'bob.williams@example.com', '6543210987', '101 Maple St, City D', 'ACTIVE'),
('CUST005', 'Charlie Brown', 'charlie.brown@example.com', '5432109876', '202 Oak St, City E', 'SUSPENDED'),
('CUST006', 'David Miller', 'david.miller@example.com', '4321098765', '303 Cedar St, City F', 'ACTIVE'),
('CUST007', 'Emma Wilson', 'emma.wilson@example.com', '3210987654', '404 Birch St, City G', 'INACTIVE'),
('CUST008', 'Franklin Carter', 'franklin.carter@example.com', '2109876543', '505 Ash St, City H', 'ACTIVE'),
('CUST009', 'Grace Lee', 'grace.lee@example.com', '1098765432', '606 Walnut St, City I', 'SUSPENDED'),
('CUST010', 'Henry Adams', 'henry.adams@example.com', '1987654321', '707 Chestnut St, City J', 'ACTIVE');