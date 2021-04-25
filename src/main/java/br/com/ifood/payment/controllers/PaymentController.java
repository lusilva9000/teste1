package br.com.ifood.payment.controllers;

import br.com.ifood.payment.resources.exceptions.IncorrectValuesException;
import br.com.ifood.payment.resources.requests.PaymentRequest;
import br.com.ifood.payment.resources.responses.PaymentResponse;
import br.com.ifood.payment.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping(path = "/payment")
    @ResponseBody
    public PaymentResponse pay(@RequestBody PaymentRequest paymentRequest) throws IncorrectValuesException {
        return new PaymentResponse(paymentService.pay(paymentRequest.getDataAsModel()));
    }
}
