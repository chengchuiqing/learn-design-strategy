package com.qing.design.bo;

import com.qing.design.domain.MsgResult;
import com.qing.design.strategy.PayStrategy;
import com.qing.design.strategy.Payment;

// 订单类
public class Order {
    private String uid;
    private String orderId;
    private double amount;

    public Order(String uid, String orderId, double amount) {
        this.uid = uid;
        this.orderId = orderId;
        this.amount = amount;
    }

    public MsgResult pay() {
        return pay(PayStrategy.DEFAULT_PAY);
    }

    public MsgResult pay(String payKey) {
        Payment payment = PayStrategy.get(payKey);
        System.out.println("欢迎使用" + payment.getName());
        System.out.println("本次交易金额为" + amount + "，开始扣款");
        return payment.pay(uid, amount);
    }
}