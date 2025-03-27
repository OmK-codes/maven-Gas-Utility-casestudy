package com.omkcodes.gasutility.service;

import com.omkcodes.gasutility.exception.ServiceRequestException;
import com.omkcodes.gasutility.model.ServiceRequest;

import java.util.List;

public interface ServiceRequestService {

    void addServiceRequest(ServiceRequest request) throws ServiceRequestException;

    ServiceRequest getServiceRequestById(String requestId) throws ServiceRequestException;

    void updateServiceRequest(ServiceRequest request) throws ServiceRequestException;

    void deleteServiceRequest(String requestId) throws ServiceRequestException;

    List<ServiceRequest> getAllServiceRequests() throws ServiceRequestException;
}
