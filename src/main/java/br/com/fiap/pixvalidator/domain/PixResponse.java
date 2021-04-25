package br.com.fiap.pixvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PixResponse {

    private String id;

    private String qrCodeId;

    private BigDecimal amount;

    private String chargeId;

    private String status;
}
