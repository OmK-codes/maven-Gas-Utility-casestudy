package com.omkcodes.gasutility.model;

import com.omkcodes.gasutility.enums.RequestStatus;
import com.omkcodes.gasutility.enums.RequestType;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ServiceRequest {
    private String requestId;
    private String customerId;
    private RequestType requestType;
    private String description;
    private String attachment;
    private RequestStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime submittedAt;
    private LocalDateTime resolvedAt;
}
