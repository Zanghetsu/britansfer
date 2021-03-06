package com.zanghetsu.britansfer.accounting.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;



@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transfer {

    @SequenceGenerator(name = "transfer_sequence", sequenceName = "transfer_sequence",allocationSize = 1)
    @Id
    @GeneratedValue(generator = "transfer_sequence",strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime dateOfTransfer;
    private TransferType transferType;
    private String transferingAccountNumber;
    private String rescievingAccountNumber;

    public Transfer(LocalDateTime dateOfTransfer, TransferType transferType, String transferingAccountNumber, String rescievingAccountNumber) {
        this.dateOfTransfer = dateOfTransfer;
        this.transferType = transferType;
        this.transferingAccountNumber = transferingAccountNumber;
        this.rescievingAccountNumber = rescievingAccountNumber;
    }

}
