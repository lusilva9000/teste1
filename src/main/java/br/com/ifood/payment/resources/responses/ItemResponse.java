package br.com.ifood.payment.resources.responses;

import br.com.ifood.payment.models.PaymentItem;

import java.math.BigDecimal;
import java.util.UUID;

public class ItemResponse {

    private UUID id;
    private String description;
    private BigDecimal price;

    public ItemResponse(PaymentItem paymentItem) {
        this.id = paymentItem.getItemId();
        this.description = paymentItem.getDescription();
        this.price = paymentItem.getValue();
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
