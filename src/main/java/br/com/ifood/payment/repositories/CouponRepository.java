package br.com.ifood.payment.repositories;

import br.com.ifood.payment.models.Coupon;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface CouponRepository extends CrudRepository<Coupon, UUID> {
}
