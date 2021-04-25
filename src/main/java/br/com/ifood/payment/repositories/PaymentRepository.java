package br.com.ifood.payment.repositories;

import br.com.ifood.payment.models.Payment;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentRepository extends CrudRepository<Payment, UUID> {
}
