package com.omkcodes.gasutility.controller;

import com.omkcodes.gasutility.enums.RequestStatus;
import com.omkcodes.gasutility.exception.ServiceRequestException;
import com.omkcodes.gasutility.model.ServiceRequest;
import com.omkcodes.gasutility.service.ServiceRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/service-requests")
public class ServiceRequestController {

    private final ServiceRequestService serviceRequestService;

    public ServiceRequestController(ServiceRequestService serviceRequestService) {
        this.serviceRequestService = serviceRequestService;
    }

    @PostMapping
    public ResponseEntity<String> createServiceRequest(@RequestBody ServiceRequest request) {
        try {
            request.setCreatedAt(LocalDateTime.now());
            serviceRequestService.addServiceRequest(request);
            return ResponseEntity.ok("Service Request created successfully!");
        } catch (ServiceRequestException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServiceRequestById(@PathVariable String id) {
        try {
            ServiceRequest request = serviceRequestService.getServiceRequestById(id);
            return ResponseEntity.ok(request);
        } catch (ServiceRequestException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {
        List<ServiceRequest> requests = serviceRequestService.getAllServiceRequests();
        return requests.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(requests);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateServiceRequest(@PathVariable String id, @RequestBody ServiceRequest updatedRequest) {
        try {
            updatedRequest.setRequestId(id);
            updatedRequest.setSubmittedAt(LocalDateTime.now());
            if (updatedRequest.getStatus() == RequestStatus.COMPLETED) {
                updatedRequest.setResolvedAt(LocalDateTime.now());
            }
            serviceRequestService.updateServiceRequest(updatedRequest);
            return ResponseEntity.ok("Service Request updated successfully!");
        } catch (ServiceRequestException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteServiceRequest(@PathVariable String id) {
        try {
            serviceRequestService.deleteServiceRequest(id);
            return ResponseEntity.ok("Service Request deleted successfully!");
        } catch (ServiceRequestException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}