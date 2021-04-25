package br.com.ifood.payment.controllers;

import br.com.ifood.payment.resources.requests.CouponRequest;
import br.com.ifood.payment.resources.responses.CouponResponse;
import br.com.ifood.payment.services.CouponService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class CouponController {

    private CouponService couponService;

    public CouponController(CouponService couponService) {
        this.couponService = couponService;
    }

    @GetMapping("/coupon/{id}")
    public CouponResponse getCoupon(@PathVariable UUID id) {
        return new CouponResponse(couponService.getCoupon(id));
    }

    @GetMapping("/coupon")
    public List<CouponResponse> listCoupons() {
        return couponService.listCoupons().stream()
                .map(coupon -> new CouponResponse(coupon))
                .collect(Collectors.toList());
    }

    @PostMapping("/coupon")
    public CouponResponse createCoupon(@RequestBody CouponRequest couponRequest) {
        var coupon = couponService.createCoupon(couponRequest.getDataAsModel());
        return new CouponResponse(coupon);
    }
}
