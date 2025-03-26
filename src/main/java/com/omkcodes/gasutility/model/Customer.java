package com.omkcodes.gasutility.model;

import com.omkcodes.gasutility.enums.CustomerStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    private String address;
    private CustomerStatus status;
}
