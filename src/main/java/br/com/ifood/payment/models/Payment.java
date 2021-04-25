package br.com.ifood.payment.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @Column(name = "order_id", updatable = false, nullable = false)
    private UUID orderId;

    @Transient
    private List<PaymentItem> items;

    private UUID couponId;

    private BigDecimal total;

    public Payment() {}

    public Payment(UUID orderId, List<PaymentItem> items, UUID couponId, BigDecimal total) {
        this.orderId = orderId;
        this.items = items;
        this.couponId = couponId;
        this.total = total;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public List<PaymentItem> getItems() {
        return items;
    }

    public void setItems(List<PaymentItem> items) {
        this.items = items;
    }

    public UUID getCouponId() {
        return couponId;
    }

    public void setCouponId(UUID couponId) {
        this.couponId = couponId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
