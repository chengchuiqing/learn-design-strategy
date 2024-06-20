package com.qing.design.controller;

import com.qing.design.bo.Order;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("pay")
public class PayController {

    @PostMapping("payment")
    public String insert() {
        Order order = new Order("1","orderid",324.5);
        return order.pay() + "";
    }

}
