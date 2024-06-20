package com.qing.design.strategy.impl;

import com.qing.design.strategy.Payment;

public class WechatPay extends Payment {
    public String getName() {
        return "微信支付";
    }

    protected double queryBalance(String uid) {
        return 263;
    }
}