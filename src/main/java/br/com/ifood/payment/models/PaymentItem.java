package br.com.ifood.payment.models;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "items")
public class PaymentItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, unique = true, nullable = false)
    private UUID id;

    @Column(name = "item_id")
    private UUID itemId;

    @Column(name = "order_id")
    private UUID orderId;

    private String description;

    private BigDecimal value;

    public PaymentItem(UUID itemId, String description, BigDecimal price) {
        this.itemId = itemId;
        this.description = description;
        this.value = price;
    }

    public PaymentItem() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getItemId() {
        return itemId;
    }

    public void setItemId(UUID itemId) {
        this.itemId = itemId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
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

    public void setValue(BigDecimal price) {
        this.value = price;
    }
}
