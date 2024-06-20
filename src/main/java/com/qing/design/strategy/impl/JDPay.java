package com.qing.design.strategy.impl;

import com.qing.design.strategy.Payment;

public class JDPay extends Payment {
    public String getName() {
        return "京东白条";
    }

    protected double queryBalance(String uid) {
        return 500;
    }
}