package com.omkcodes.gasutility.repository;

import com.omkcodes.gasutility.enums.RequestStatus;
import com.omkcodes.gasutility.enums.RequestType;
import com.omkcodes.gasutility.model.ServiceRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Repository
public class ServiceRequestRepository {

    private final JdbcTemplate jdbcTemplate;

    public ServiceRequestRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<ServiceRequest> serviceRequestRowMapper = (rs, rowNum) -> new ServiceRequest(
            rs.getString("id"),
            rs.getString("customer_id"),
            RequestType.valueOf(rs.getString("request_type")),
            rs.getString("description"),
            rs.getString("attachment"),
            RequestStatus.valueOf(rs.getString("status")),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("submitted_at") != null ? rs.getTimestamp("submitted_at").toLocalDateTime() : null,
            rs.getTimestamp("resolved_at") != null ? rs.getTimestamp("resolved_at").toLocalDateTime() : null
    );

    public boolean saveServiceRequest(ServiceRequest request) {
        String query = """
                INSERT INTO service_request (id, customer_id, request_type, description, attachment, 
                                             status, created_at, submitted_at, resolved_at)
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        return jdbcTemplate.update(query,
                request.getRequestId().trim(),
                request.getCustomerId().trim(),
                request.getRequestType().name(),
                request.getDescription().trim(),
                request.getAttachment() != null ? request.getAttachment().trim() : null,
                request.getStatus().name(),
                Timestamp.valueOf(request.getCreatedAt()),
                request.getSubmittedAt() != null ? Timestamp.valueOf(request.getSubmittedAt()) : null,
                request.getResolvedAt() != null ? Timestamp.valueOf(request.getResolvedAt()) : null
        ) > 0;
    }

    public Optional<ServiceRequest> findServiceRequestById(String requestId) {
        String query = "SELECT * FROM service_request WHERE id = ?";
        List<ServiceRequest> requests = jdbcTemplate.query(query, serviceRequestRowMapper, requestId.trim());
        return requests.isEmpty() ? Optional.empty() : Optional.of(requests.get(0));
    }

    public List<ServiceRequest> getAllServiceRequests() {
        String query = "SELECT * FROM service_request";
        return jdbcTemplate.query(query, serviceRequestRowMapper);
    }

    public boolean updateServiceRequest(ServiceRequest request) {
        String query = """
                UPDATE service_request
                SET request_type = ?, description = ?, attachment = ?, status = ?, 
                    submitted_at = ?, resolved_at = ?
                WHERE id = ?
                """;
        return jdbcTemplate.update(query,
                request.getRequestType().name(),
                request.getDescription().trim(),
                request.getAttachment() != null ? request.getAttachment().trim() : null,
                request.getStatus().name(),
                request.getSubmittedAt() != null ? Timestamp.valueOf(request.getSubmittedAt()) : null,
                request.getResolvedAt() != null ? Timestamp.valueOf(request.getResolvedAt()) : null,
                request.getRequestId().trim()
        ) > 0;
    }

    public boolean deleteServiceRequest(String requestId) {
        String query = "DELETE FROM service_request WHERE id = ?";
        return jdbcTemplate.update(query, requestId.trim()) > 0;
    }
}