package com.omkcodes.gasutility.enums;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum RequestType {
    INSTALLATION(1),
    REPAIR(2),
    BILLING_ISSUE(3),
    OTHER(4);

    private final int code;

    // Efficient lookup map for retrieving enum by code
    private static final Map<Integer, RequestType> CODE_TO_TYPE =
            Stream.of(values()).collect(Collectors.toMap(RequestType::getCode, Function.identity()));

    RequestType(int code) {
        this.code = code;
    }

    // Method to get enum from integer code
    public static RequestType fromCode(int code) {
        return CODE_TO_TYPE.getOrDefault(code, null); // Returns null if code is invalid
    }

    @Override
    public String toString() {
        return name() + " (" + code + ")";
    }
}
