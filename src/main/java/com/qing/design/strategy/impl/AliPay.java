package com.qing.design.strategy.impl;

import com.qing.design.strategy.Payment;

public class AliPay extends Payment {
    public String getName() {
        return "支付宝";
    }

    protected double queryBalance(String uid) {
        return 900;
    }
}