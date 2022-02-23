package com.zanghetsu.britansfer.accounting.controller.transferRequest;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class TransferRequest {
    private final String accountNumber1;
    private final String accountNumber2;
    private final int amount;
}
