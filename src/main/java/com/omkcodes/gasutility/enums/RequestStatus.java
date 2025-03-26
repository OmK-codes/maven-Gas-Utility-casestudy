package com.omkcodes.gasutility.enums;

import lombok.Getter;

@Getter
public enum RequestStatus {
    NEW(1),
    IN_PROGRESS(2),
    COMPLETED(3),
    CANCELLED(4);

    private final int code;

    RequestStatus(int code) {
        this.code = code;}
}