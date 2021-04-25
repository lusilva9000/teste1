package br.com.ifood.payment.repositories;

import br.com.ifood.payment.models.PaymentItem;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentItemRepository extends CrudRepository<PaymentItem, UUID> {
}
