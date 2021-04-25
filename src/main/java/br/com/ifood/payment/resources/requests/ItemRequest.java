package br.com.ifood.payment.resources.requests;

import br.com.ifood.payment.models.PaymentItem;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public class ItemRequest {

    @NotNull
    @br.com.ifood.payment.infra.UUID
    private UUID id;

    @NotNull
    @Length(max = 50)
    private String description;

    @NotNull
    private BigDecimal price;

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

    public PaymentItem getDataAsModel() {
        return new PaymentItem(
                this.id,
                this.description,
                this.price
        );
    }
}
