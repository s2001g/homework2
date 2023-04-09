package com.tristan.orders;

import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class OrderController {

    @GlobalTransactional(rollbackFor = Exception.class)
    @RequestMapping("/create")
    public String create(int accountId) throws IOException, InterruptedException {
//        System.out.println("Controller全局事务id=================>{}"+ RootContext.getXID());
        OrdersService service = new OrdersService();
        return service.addOrder(accountId);
    }
}
