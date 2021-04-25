package br.com.ifood.payment.resources.responses;

import br.com.ifood.payment.models.Coupon;

import java.math.BigDecimal;
import java.util.UUID;

public class CouponResponse {

    private UUID id;

    private String description;

    private BigDecimal value;

    public CouponResponse(Coupon coupon) {
        this.id = coupon.getId();
        this.description = coupon.getDescription();
        this.value = coupon.getValue();
    }

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
}
