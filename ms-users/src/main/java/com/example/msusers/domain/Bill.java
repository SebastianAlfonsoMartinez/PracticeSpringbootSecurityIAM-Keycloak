package com.example.msusers.domain;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class Bill {
    private String idBill;
    private String customerBill;
    private String productBill;
    private Double totalPrice;
}
