CREATE TABLE service_request (
    request_id VARCHAR(50) PRIMARY KEY,
    customer_id VARCHAR(50) NOT NULL,
    request_type ENUM('INSTALLATION', 'MAINTENANCE', 'BILLING', 'OTHER') NOT NULL,
    description TEXT NOT NULL,
    attachment VARCHAR(255), -- Assuming this stores file paths
    status ENUM('PENDING', 'IN_PROGRESS', 'COMPLETED', 'CANCELLED') DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    submitted_at TIMESTAMP NULL,
    resolved_at TIMESTAMP NULL,
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id) ON DELETE CASCADE
);
INSERT INTO service_request (request_id, customer_id, request_type, description, attachment, status, created_at, submitted_at, resolved_at) VALUES
('REQ001', 'CUST001', 'INSTALLATION', 'Gas meter installation', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ002', 'CUST002', 'MAINTENANCE', 'Gas leakage repair', NULL, 'IN_PROGRESS', NOW(), NOW(), NULL),
('REQ003', 'CUST003', 'BILLING', 'Incorrect bill amount', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ004', 'CUST004', 'OTHER', 'General inquiry about services', NULL, 'COMPLETED', NOW(), NOW(), NOW()),
('REQ005', 'CUST005', 'INSTALLATION', 'New gas connection', NULL, 'CANCELLED', NOW(), NOW(), NULL),
('REQ006', 'CUST006', 'MAINTENANCE', 'Routine gas pipeline check', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ007', 'CUST007', 'BILLING', 'Billing discrepancy for last month', NULL, 'IN_PROGRESS', NOW(), NOW(), NULL),
('REQ008', 'CUST008', 'OTHER', 'Request for gas usage history', NULL, 'COMPLETED', NOW(), NOW(), NOW()),
('REQ009', 'CUST009', 'INSTALLATION', 'Upgrading gas meter', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ010', 'CUST010', 'MAINTENANCE', 'Emergency gas leak check', NULL, 'IN_PROGRESS', NOW(), NOW(), NULL),
('REQ011', 'CUST001', 'BILLING', 'Request for bill breakdown', NULL, 'COMPLETED', NOW(), NOW(), NOW()),
('REQ012', 'CUST002', 'INSTALLATION', 'Installation of new burner', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ013', 'CUST003', 'MAINTENANCE', 'Gas regulator checkup', NULL, 'CANCELLED', NOW(), NOW(), NULL),
('REQ014', 'CUST004', 'BILLING', 'Late fee waiver request', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ015', 'CUST005', 'OTHER', 'Gas safety guidelines request', NULL, 'COMPLETED', NOW(), NOW(), NOW()),
('REQ016', 'CUST006', 'INSTALLATION', 'Pipeline expansion request', NULL, 'IN_PROGRESS', NOW(), NOW(), NULL),
('REQ017', 'CUST007', 'MAINTENANCE', 'Annual gas maintenance check', NULL, 'PENDING', NOW(), NULL, NULL),
('REQ018', 'CUST008', 'BILLING', 'Recalculation of last month\'s bill', NULL, 'CANCELLED', NOW(), NOW(), NULL),
('REQ019', 'CUST009', 'OTHER', 'Request for emergency contact details', NULL, 'COMPLETED', NOW(), NOW(), NOW()),
('REQ020', 'CUST010', 'INSTALLATION', 'Gas pipeline relocation', NULL, 'PENDING', NOW(), NULL, NULL);