package com.omkcodes.gasutility.enums;

import lombok.Getter;

@Getter
public enum CustomerStatus {
    ACTIVE(1),
    INACTIVE(2),
    SUSPENDED(3),
    BLACKLISTED(4);

    private final int code;

    CustomerStatus(int code) {
        this.code = code;
    }
}
