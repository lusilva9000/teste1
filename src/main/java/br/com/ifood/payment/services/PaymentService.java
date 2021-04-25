package br.com.ifood.payment.services;

import br.com.ifood.payment.models.Payment;
import br.com.ifood.payment.repositories.PaymentItemRepository;
import br.com.ifood.payment.repositories.PaymentRepository;
import br.com.ifood.payment.resources.exceptions.IncorrectValuesException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private PaymentRepository paymentRepository;

    private PaymentItemRepository paymentItemRepository;

    private CouponService couponService;

    public PaymentService(
            PaymentRepository paymentRepository,
            CouponService couponService,
            PaymentItemRepository paymentItemRepository) {
        this.paymentRepository = paymentRepository;
        this.paymentItemRepository = paymentItemRepository;
        this.couponService = couponService;
    }

    public Payment pay(Payment payment) throws IncorrectValuesException {

        validateValues(payment);

        paymentRepository.save(payment);

        payment.getItems().forEach(item -> item.setOrderId(payment.getOrderId()));
        paymentItemRepository.saveAll(payment.getItems());

        return payment;
    }

    private void validateValues(Payment payment) throws IncorrectValuesException {

        var couponValue = new BigDecimal(0.0);

        if (payment.getCouponId() != null) {
            var coupon = couponService.getCoupon(payment.getCouponId());
            couponValue = coupon.getValue();
        }

        var itemsValue = payment.getItems()
                .stream()
                .reduce(new BigDecimal(0.0), (partial, nextItem) -> partial.add(nextItem.getValue()), BigDecimal::add);

        if (payment.getTotal().compareTo(itemsValue.subtract(couponValue)) < 0) {
            throw new IncorrectValuesException("Invalid values. Check and retry.");
        }
    }
}
