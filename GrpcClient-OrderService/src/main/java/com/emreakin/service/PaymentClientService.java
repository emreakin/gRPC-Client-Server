package com.emreakin.service;

import com.emreakin.model.BuyOrderRequest;
import com.emreakin.model.BuyOrderResponse;
import com.grpc.payment.PaymentRequest;
import com.grpc.payment.PaymentResponse;
import com.grpc.payment.PaymentServiceGrpc;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentClientService {

    @GrpcClient("payment-service")
    private PaymentServiceGrpc.PaymentServiceBlockingStub paymentServiceBlockingStub;

    public BuyOrderResponse payOrder(BuyOrderRequest buyOrderRequest) {

        PaymentResponse paymentResponse = paymentServiceBlockingStub.payOrder(
                PaymentRequest.newBuilder()
                        .setOrderId(buyOrderRequest.getOrderId())
                        .setPrice(buyOrderRequest.getPrice())
                        .setPrepaid(buyOrderRequest.isPrepaid())
                        .setPaymentUser(buyOrderRequest.getPaymentUser())
                        .build());

        return BuyOrderResponse.builder()
                .success(paymentResponse.getSuccess())
                .message(paymentResponse.getMessage())
                .build();
    }
}
