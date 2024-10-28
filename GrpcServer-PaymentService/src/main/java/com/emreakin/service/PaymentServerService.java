package com.emreakin.service;

import com.grpc.payment.PaymentRequest;
import com.grpc.payment.PaymentResponse;
import com.grpc.payment.PaymentServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@RequiredArgsConstructor
@GrpcService
public class PaymentServerService extends PaymentServiceGrpc.PaymentServiceImplBase {

    private final BankService bankService;

    @Override
    public void payOrder(PaymentRequest paymentRequest, StreamObserver<PaymentResponse> responseObserver) {
        boolean paymentSuccess = bankService.makePayment(paymentRequest.getPrice(), paymentRequest.getPrepaid());

        PaymentResponse paymentResponse = PaymentResponse.newBuilder()
                .setSuccess(paymentSuccess)
                .setMessage(paymentSuccess ? "Successfully paid" : "Payment failed")
                .build();

        responseObserver.onNext(paymentResponse);
        responseObserver.onCompleted();
    }
}
