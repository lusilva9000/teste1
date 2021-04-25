package br.com.ifood.payment.resources.responses;

import br.com.ifood.payment.models.Payment;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PaymentResponse {

    private UUID orderId;

    private List<ItemResponse> items;

    private UUID couponId;

    private BigDecimal total;

    public PaymentResponse(Payment payment) {
        this.orderId = payment.getOrderId();
        this.items = payment.getItems()
                .stream()
                .map(item -> new ItemResponse(item))
                .collect(Collectors.toList());
        this.couponId = payment.getCouponId();
        this.total = payment.getTotal();
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public List<ItemResponse> getItems() {
        return items;
    }

    public void setItems(List<ItemResponse> items) {
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
