package com.qing.design.strategy.impl;

import com.qing.design.strategy.Payment;

public class UnionPay extends Payment {
    public String getName() {
        return "银联支付";
    }

    protected double queryBalance(String uid) {
        return 120;
    }
}