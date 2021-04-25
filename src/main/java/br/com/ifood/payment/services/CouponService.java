package br.com.ifood.payment.services;

import br.com.ifood.payment.models.Coupon;
import br.com.ifood.payment.repositories.CouponRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CouponService {

    private CouponRepository couponRepository;

    public CouponService(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    public Coupon getCoupon(UUID couponId) {
        return couponRepository.findById(couponId).get();
    }

    @Cacheable(cacheNames = "coupons")
    public List<Coupon> listCoupons() {
        var list = new ArrayList<Coupon>();
        couponRepository.findAll().forEach(list::add);
        return list;
    }

    @CacheEvict(cacheNames = "coupons", allEntries = true)
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }
}
