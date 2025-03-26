package com.omkcodes.gasutility.service.impl;

import com.omkcodes.gasutility.exception.ServiceRequestException;
import com.omkcodes.gasutility.model.ServiceRequest;
import com.omkcodes.gasutility.repository.ServiceRequestRepository;
import com.omkcodes.gasutility.service.ServiceRequestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

    private final ServiceRequestRepository repository;

    public ServiceRequestServiceImpl(ServiceRequestRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void addServiceRequest(ServiceRequest request) throws ServiceRequestException {
        if (request == null) {
            throw new ServiceRequestException("Service request cannot be null.");
        }

        // Ensure createdAt is set before saving
        if (request.getCreatedAt() == null) {
            request.setCreatedAt(LocalDateTime.now());
        }

        boolean isSaved = repository.saveServiceRequest(request);
        if (!isSaved) {
            throw new ServiceRequestException("Failed to save service request.");
        }
    }

    @Override
    public ServiceRequest getServiceRequestById(String requestId) throws ServiceRequestException {
        if (requestId == null || requestId.trim().isEmpty()) {
            throw new ServiceRequestException("Service request ID cannot be null or empty.");
        }

        return repository.findServiceRequestById(requestId)
                .orElseThrow(() -> new ServiceRequestException("Service request not found with ID: " + requestId));
    }

    @Override
    @Transactional
    public void updateServiceRequest(ServiceRequest request) throws ServiceRequestException {
        if (request == null || request.getRequestId() == null || request.getRequestId().trim().isEmpty()) {
            throw new ServiceRequestException("Invalid service request for update.");
        }

        Optional<ServiceRequest> existingRequest = repository.findServiceRequestById(request.getRequestId());
        if (existingRequest.isEmpty()) {
            throw new ServiceRequestException("Service request not found with ID: " + request.getRequestId());
        }

        // Handle potential null timestamps before updating
        if (request.getSubmittedAt() == null) {
            request.setSubmittedAt(existingRequest.get().getSubmittedAt());
        }
        if (request.getResolvedAt() == null) {
            request.setResolvedAt(existingRequest.get().getResolvedAt());
        }

        boolean isUpdated = repository.updateServiceRequest(request);
        if (!isUpdated) {
            throw new ServiceRequestException("Failed to update service request.");
        }
    }

    @Override
    @Transactional
    public void deleteServiceRequest(String requestId) throws ServiceRequestException {
        if (requestId == null || requestId.trim().isEmpty()) {
            throw new ServiceRequestException("Service request ID cannot be null or empty.");
        }

        Optional<ServiceRequest> existingRequest = repository.findServiceRequestById(requestId);
        if (existingRequest.isEmpty()) {
            throw new ServiceRequestException("Service request not found with ID: " + requestId);
        }

        boolean isDeleted = repository.deleteServiceRequest(requestId);
        if (!isDeleted) {
            throw new ServiceRequestException("Failed to delete service request.");
        }
    }

    @Override
    public List<ServiceRequest> getAllServiceRequests() {
        return repository.getAllServiceRequests();
    }
}