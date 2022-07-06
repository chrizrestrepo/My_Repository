package org.cyrestrepo.test.springboot.app.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
public class TransferDTO {
    private Long accountIdOrigin;
    private Long accountIdDestiny;
    private BigDecimal amount;
    private Long bankId;
}
