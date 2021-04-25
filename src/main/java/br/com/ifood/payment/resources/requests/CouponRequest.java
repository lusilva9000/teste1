package br.com.ifood.payment.resources.requests;

import br.com.ifood.payment.models.Coupon;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class CouponRequest {

    @NotNull
    @br.com.ifood.payment.infra.UUID
    private UUID id;

    @NotNull
    @Length(max = 50)
    private String description;

    @NotNull
    private BigDecimal value;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Coupon getDataAsModel() {
        return new Coupon(
                this.id,
                this.description,
                this.value
        );
    }
}
