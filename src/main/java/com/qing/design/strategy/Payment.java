package com.qing.design.strategy;

import com.qing.design.domain.MsgResult;

// 定义支付逻辑，具体支付交由子类实现
public abstract class Payment {

    public abstract String getName();

    //通用逻辑放到抽象类里面实现
    public MsgResult pay(String uid, double amount) {
        // 判断余额是否足够
        if (queryBalance(uid) < amount) {
            return new MsgResult(500, "支付失败", "余额不足");
        }
        return new MsgResult(200, "支付成功" + amount, null);
    }

    protected abstract double queryBalance(String uid);
}
