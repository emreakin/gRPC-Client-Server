package com.emreakin.model;

import lombok.Data;

@Data
public class BuyOrderRequest {

    private String orderId;
    private int price;
    private boolean prepaid;
    private String paymentUser;
}
