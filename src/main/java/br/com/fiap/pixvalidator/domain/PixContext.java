package br.com.fiap.pixvalidator.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PixContext {

    private String id;

    private String qrCodeId;

    private BigDecimal amount;

    private String chargeId;

    private String status;

    public PixInfo toPixInfo(PixContext context) {
        return PixInfo.builder()
                .id(UUID.randomUUID().toString())
                .qrCodeId(context.getQrCodeId())
                .amount(context.getAmount())
                .chargeId("")
                .status(context.getStatus())
                .build();
    }

    public PixInfo toUpdatedPixInfo(PixContext context) {
        return PixInfo.builder()
                .id(context.getId())
                .qrCodeId(context.getQrCodeId())
                .amount(context.getAmount())
                .chargeId(context.getChargeId())
                .status("VALIDATED")
                .build();
    }

    public PixResponse toPixResponse() {
        return PixResponse.builder()
                .id(this.getId())
                .qrCodeId(this.getQrCodeId())
                .amount(this.getAmount())
                .chargeId(this.getChargeId())
                .status(this.getStatus())
                .build();
    }

    public PixContext addPixId(PixInfo pixInfo){
        id = pixInfo.getId();

        return this;
    }
}
