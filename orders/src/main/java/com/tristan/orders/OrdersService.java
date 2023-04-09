package com.tristan.orders;

import com.tristan.orders.dao.OrdersDAO;
import com.tristan.orders.po.Orders;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class OrdersService {

    @Autowired
    private OrderManager orderManager;

    public String addOrder(int accountId) throws IOException, InterruptedException {

//        System.out.println("Service全局事务id=================>{}"+RootContext.getXID());
        OrdersDAO dao = new OrdersDAO();
        Orders orders = dao.createOrders(orderManager.readomOrders(accountId));
        orderManager.increasePoints(orders.getAccountId(), orders.getInfo());

        return  null;
    }


}
