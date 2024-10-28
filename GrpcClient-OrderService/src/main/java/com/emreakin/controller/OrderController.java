package com.emreakin.controller;

import com.emreakin.model.BuyOrderRequest;
import com.emreakin.model.BuyOrderResponse;
import com.emreakin.service.PaymentClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/order")
@RequiredArgsConstructor
public class OrderController {

    private final PaymentClientService paymentClientService;

    @PostMapping("/buyOrder")
    public BuyOrderResponse buyOrder(@RequestBody BuyOrderRequest buyOrderRequest) {
        return paymentClientService.payOrder(buyOrderRequest);
    }
}
