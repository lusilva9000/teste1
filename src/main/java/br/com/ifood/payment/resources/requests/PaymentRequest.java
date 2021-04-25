package br.com.ifood.payment.resources.requests;

import br.com.ifood.payment.models.Payment;
import com.fasterxml.jackson.annotation.JsonAlias;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class PaymentRequest {

    @JsonAlias("order_id")
    @NotNull
    @br.com.ifood.payment.infra.UUID
    private UUID orderId;

    @NotNull
    @NotEmpty
    private List<ItemRequest> items;

    @JsonAlias("coupon_id")
    @br.com.ifood.payment.infra.UUID
    private UUID couponId;

    private BigDecimal total;

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public List<ItemRequest> getItems() {
        return items;
    }

    public void setItems(List<ItemRequest> items) {
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

    public Payment getDataAsModel() {
        var items = this.items
                .stream()
                .map(item -> item.getDataAsModel())
                .collect(Collectors.toList());

        return new Payment(
                this.orderId,
                items,
                this.couponId,
                this.total
        );
    }
}
