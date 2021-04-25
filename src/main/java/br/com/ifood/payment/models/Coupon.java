package br.com.ifood.payment.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "coupons")
public class Coupon implements Serializable {

    @Id
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    private String description;

    private BigDecimal value;

    public Coupon() {}

    public Coupon(UUID id, String description, BigDecimal value) {
        this.id = id;
        this.description = description;
        this.value = value;
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
